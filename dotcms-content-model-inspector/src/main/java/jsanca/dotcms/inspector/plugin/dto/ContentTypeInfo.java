package jsanca.dotcms.inspector.plugin.dto;

import java.util.List;

public class ContentTypeInfo {

    private final String id;
    private final String inode;
    private final String name;
    private final String variable;
    private final String baseType;
    private final String description;
    private final boolean system;
    private final List<FieldInfo> fields;

    public ContentTypeInfo(String id, String inode, String name, String variable,
                           String baseType, String description, boolean system, List<FieldInfo> fields) {
        this.id = id;
        this.inode = inode;
        this.name = name;
        this.variable = variable;
        this.baseType = baseType;
        this.description = description;
        this.system = system;
        this.fields = fields;
    }

    public String getId() { return id; }
    public String getInode() { return inode; }
    public String getName() { return name; }
    public String getVariable() { return variable; }
    public String getBaseType() { return baseType; }
    public String getDescription() { return description; }
    public boolean isSystem() { return system; }
    public List<FieldInfo> getFields() { return fields; }
}
