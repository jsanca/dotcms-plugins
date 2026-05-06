package jsanca.dotcms.inspector.plugin.service;

import com.dotcms.contenttype.business.ContentTypeAPI;
import com.dotcms.contenttype.model.field.ContentTypeFieldProperties;
import com.dotcms.contenttype.model.field.Field;
import com.dotcms.contenttype.model.field.FieldBuilder;
import com.dotcms.contenttype.model.field.FieldTypeAPI;
import com.dotcms.contenttype.model.field.LegacyFieldTypes;
import com.dotcms.contenttype.model.type.BaseContentType;
import com.dotcms.contenttype.model.type.ContentType;
import com.dotmarketing.beans.Host;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.portlets.contentlet.business.HostAPI;
import com.dotmarketing.util.Logger;
import jsanca.dotcms.inspector.plugin.dto.BaseTypeInfo;
import jsanca.dotcms.inspector.plugin.dto.ContentTypeInfo;
import jsanca.dotcms.inspector.plugin.dto.FieldInfo;
import jsanca.dotcms.inspector.plugin.dto.FieldTypeDefinitionInfo;
import jsanca.dotcms.inspector.plugin.dto.SiteInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DotCmsMetadataService {

    public List<FieldTypeDefinitionInfo> listFieldTypes() {
        try {
            return FieldTypeAPI.getInstance()
                    .getFieldTypes(APILocator.systemUser())
                    .stream()
                    .map(this::toFieldTypeDefinitionInfo)
                    .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            Logger.error(this.getClass(), "Error listing field types: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private FieldTypeDefinitionInfo toFieldTypeDefinitionInfo(
            final com.dotcms.contenttype.model.field.FieldType ft) {

        final String className = ft.getClazz();
        final String name = className.contains(".")
                ? className.substring(className.lastIndexOf('.') + 1)
                : className;

        String legacyName = null;
        try {
            legacyName = LegacyFieldTypes.getLegacyName(className);
        } catch (Exception ignored) {
            // not every field type has a legacy name
        }

        final Collection<ContentTypeFieldProperties> props = ft.getProperties();
        final List<String> propNames = props != null
                ? props.stream().map(Enum::name).collect(Collectors.toList())
                : Collections.emptyList();

        // Try to get a prototype instance for dataType / acceptedDataTypes / fieldVariableKeys
        String dataType = null;
        List<String> acceptedDataTypes = null;
        List<String> fieldVariableKeys = null;
        boolean supportsReadOnly = false;
        boolean supportsFixed = false;
        try {
            final Class<?> clazz = Class.forName(className);
            final Field prototype = FieldBuilder.instanceOf(clazz);
            if (prototype != null) {
                if (prototype.dataType() != null) {
                    dataType = prototype.dataType().name();
                }
                if (prototype.acceptedDataTypes() != null) {
                    acceptedDataTypes = prototype.acceptedDataTypes().stream()
                            .map(Enum::name)
                            .collect(Collectors.toList());
                }
                if (prototype.fieldVariableKeys() != null) {
                    fieldVariableKeys = prototype.fieldVariableKeys();
                }
                supportsReadOnly = prototype.readOnly();
                supportsFixed = prototype.fixed();
            }
        } catch (Exception ignored) {
            // prototype instantiation may fail for some field types — values remain null
        }

        return new FieldTypeDefinitionInfo(
                name,
                className,
                legacyName,
                ft.getLabel(),
                ft.getHelpText(),
                dataType,
                acceptedDataTypes,
                fieldVariableKeys,
                propNames,
                hasProperty(props, ContentTypeFieldProperties.VALUES)
                        || hasProperty(props, ContentTypeFieldProperties.CATEGORIES)
                        || hasProperty(props, ContentTypeFieldProperties.RELATIONSHIPS),
                hasProperty(props, ContentTypeFieldProperties.DEFAULT_VALUE),
                hasProperty(props, ContentTypeFieldProperties.REGEX_CHECK),
                hasProperty(props, ContentTypeFieldProperties.HINT),
                hasProperty(props, ContentTypeFieldProperties.REQUIRED),
                hasProperty(props, ContentTypeFieldProperties.INDEXED),
                hasProperty(props, ContentTypeFieldProperties.LISTED),
                hasProperty(props, ContentTypeFieldProperties.SEARCHABLE),
                hasProperty(props, ContentTypeFieldProperties.UNIQUE),
                supportsReadOnly,
                supportsFixed
        );
    }

    private boolean hasProperty(final Collection<ContentTypeFieldProperties> props,
                                final ContentTypeFieldProperties target) {
        return props != null && props.contains(target);
    }

    public List<BaseTypeInfo> listBaseTypes() {
        return BaseContentType.allBaseTypes().stream()
                .map(bt -> new BaseTypeInfo(
                        bt.getType(),
                        bt.name(),
                        bt.getAlternateName(),
                        bt.immutableClass().getSimpleName()
                ))
                .collect(Collectors.toList());
    }

    public List<SiteInfo> listSites() {
        try {
            final HostAPI hostAPI = APILocator.getHostAPI();
            final List<Host> hosts = hostAPI.findAll(APILocator.systemUser(), false);
            final List<SiteInfo> result = new ArrayList<>();
            for (final Host host : hosts) {
                result.add(toSiteInfo(hostAPI, host));
            }
            return result;
        } catch (Exception e) {
            Logger.error(this.getClass(), "Error listing sites: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<ContentTypeInfo> listContentTypes() {
        try {
            final ContentTypeAPI ctAPI = APILocator.getContentTypeAPI(APILocator.systemUser());
            return ctAPI.findAll().stream()
                    .map(this::toContentTypeInfo)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            Logger.error(this.getClass(), "Error listing content types: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<FieldInfo> findFields(final String variableOrId) {
        try {
            final ContentTypeAPI ctAPI = APILocator.getContentTypeAPI(APILocator.systemUser());
            final ContentType ct = ctAPI.find(variableOrId);
            if (ct == null) {
                return Collections.emptyList();
            }
            return ct.fields().stream()
                    .map(this::toFieldInfo)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            Logger.error(this.getClass(), "Error finding fields for '" + variableOrId + "': " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private ContentTypeInfo toContentTypeInfo(final ContentType ct) {
        final List<FieldInfo> fields = ct.fields().stream()
                .map(this::toFieldInfo)
                .collect(Collectors.toList());
        return new ContentTypeInfo(
                ct.id(),
                ct.inode(),
                ct.name(),
                ct.variable(),
                ct.baseType().name(),
                ct.description(),
                ct.system(),
                fields
        );
    }

    private FieldInfo toFieldInfo(final Field f) {
        return new FieldInfo(
                f.id(),
                f.inode(),
                f.name(),
                f.variable(),
                f.typeName(),
                f.required(),
                f.indexed(),
                f.searchable(),
                f.listed(),
                f.sortOrder(),
                f.defaultValue(),
                f.values(),
                f.hint(),
                f.fixed(),
                f.readOnly(),
                f.unique()
        );
    }

    private SiteInfo toSiteInfo(final HostAPI hostAPI, final Host host) {
        final List<String> aliases = hostAPI.parseHostAliases(host);
        boolean archived = false;
        try {
            archived = host.isArchived();
        } catch (Exception e) {
            // isArchived() can throw DotDataException/DotSecurityException — default false
        }
        return new SiteInfo(
                host.getInode(),
                host.getIdentifier(),
                host.getName(),
                host.getHostname(),
                aliases != null ? aliases : Collections.emptyList(),
                host.isDefault(),
                host.isSystemHost(),
                archived
        );
    }
}
