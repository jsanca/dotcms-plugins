package jsanca.dotcms.inspector.plugin.dto;

import java.util.List;

public class AiContextResponse {

    private final List<SiteInfo> sites;
    private final List<BaseTypeInfo> baseTypes;
    private final List<ContentTypeInfo> contentTypes;
    private final List<FieldTypeDefinitionInfo> fieldTypes;
    private final LayoutCapabilities layoutCapabilities;
    private final AgentGuide agentGuide;

    public AiContextResponse(List<SiteInfo> sites, List<BaseTypeInfo> baseTypes,
                             List<ContentTypeInfo> contentTypes,
                             List<FieldTypeDefinitionInfo> fieldTypes,
                             LayoutCapabilities layoutCapabilities, AgentGuide agentGuide) {
        this.sites = sites;
        this.baseTypes = baseTypes;
        this.contentTypes = contentTypes;
        this.fieldTypes = fieldTypes;
        this.layoutCapabilities = layoutCapabilities;
        this.agentGuide = agentGuide;
    }

    public List<SiteInfo> getSites() { return sites; }
    public List<BaseTypeInfo> getBaseTypes() { return baseTypes; }
    public List<ContentTypeInfo> getContentTypes() { return contentTypes; }
    public List<FieldTypeDefinitionInfo> getFieldTypes() { return fieldTypes; }
    public LayoutCapabilities getLayoutCapabilities() { return layoutCapabilities; }
    public AgentGuide getAgentGuide() { return agentGuide; }
}
