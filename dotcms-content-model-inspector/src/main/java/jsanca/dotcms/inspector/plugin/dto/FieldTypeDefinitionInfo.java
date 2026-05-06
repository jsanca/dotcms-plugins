package jsanca.dotcms.inspector.plugin.dto;

import java.util.List;

public class FieldTypeDefinitionInfo {

    private final String name;
    private final String className;
    private final String legacyName;
    private final String labelKey;
    private final String helpTextKey;
    private final String dataType;
    private final List<String> acceptedDataTypes;
    private final List<String> fieldVariableKeys;
    private final List<String> contentTypeProperties;
    private final boolean supportsValues;
    private final boolean supportsDefaultValue;
    private final boolean supportsRegexCheck;
    private final boolean supportsHint;
    private final boolean supportsRequired;
    private final boolean supportsIndexed;
    private final boolean supportsListed;
    private final boolean supportsSearchable;
    private final boolean supportsUnique;
    private final boolean supportsReadOnly;
    private final boolean supportsFixed;

    public FieldTypeDefinitionInfo(String name, String className, String legacyName,
                                   String labelKey, String helpTextKey,
                                   String dataType, List<String> acceptedDataTypes,
                                   List<String> fieldVariableKeys, List<String> contentTypeProperties,
                                   boolean supportsValues, boolean supportsDefaultValue,
                                   boolean supportsRegexCheck, boolean supportsHint,
                                   boolean supportsRequired, boolean supportsIndexed,
                                   boolean supportsListed, boolean supportsSearchable,
                                   boolean supportsUnique, boolean supportsReadOnly,
                                   boolean supportsFixed) {
        this.name = name;
        this.className = className;
        this.legacyName = legacyName;
        this.labelKey = labelKey;
        this.helpTextKey = helpTextKey;
        this.dataType = dataType;
        this.acceptedDataTypes = acceptedDataTypes;
        this.fieldVariableKeys = fieldVariableKeys;
        this.contentTypeProperties = contentTypeProperties;
        this.supportsValues = supportsValues;
        this.supportsDefaultValue = supportsDefaultValue;
        this.supportsRegexCheck = supportsRegexCheck;
        this.supportsHint = supportsHint;
        this.supportsRequired = supportsRequired;
        this.supportsIndexed = supportsIndexed;
        this.supportsListed = supportsListed;
        this.supportsSearchable = supportsSearchable;
        this.supportsUnique = supportsUnique;
        this.supportsReadOnly = supportsReadOnly;
        this.supportsFixed = supportsFixed;
    }

    public String getName() { return name; }
    public String getClassName() { return className; }
    public String getLegacyName() { return legacyName; }
    public String getLabelKey() { return labelKey; }
    public String getHelpTextKey() { return helpTextKey; }
    public String getDataType() { return dataType; }
    public List<String> getAcceptedDataTypes() { return acceptedDataTypes; }
    public List<String> getFieldVariableKeys() { return fieldVariableKeys; }
    public List<String> getContentTypeProperties() { return contentTypeProperties; }
    public boolean isSupportsValues() { return supportsValues; }
    public boolean isSupportsDefaultValue() { return supportsDefaultValue; }
    public boolean isSupportsRegexCheck() { return supportsRegexCheck; }
    public boolean isSupportsHint() { return supportsHint; }
    public boolean isSupportsRequired() { return supportsRequired; }
    public boolean isSupportsIndexed() { return supportsIndexed; }
    public boolean isSupportsListed() { return supportsListed; }
    public boolean isSupportsSearchable() { return supportsSearchable; }
    public boolean isSupportsUnique() { return supportsUnique; }
    public boolean isSupportsReadOnly() { return supportsReadOnly; }
    public boolean isSupportsFixed() { return supportsFixed; }
}
