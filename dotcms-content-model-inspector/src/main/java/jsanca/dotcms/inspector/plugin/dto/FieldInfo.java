package jsanca.dotcms.inspector.plugin.dto;

public class FieldInfo {

    private final String id;
    private final String inode;
    private final String name;
    private final String variable;
    private final String type;
    private final boolean required;
    private final boolean indexed;
    private final boolean searchable;
    private final boolean listed;
    private final int sortOrder;
    private final String defaultValue;
    private final String values;
    private final String hint;
    private final boolean fixed;
    private final boolean readOnly;
    private final boolean unique;

    public FieldInfo(String id, String inode, String name, String variable, String type,
                     boolean required, boolean indexed, boolean searchable, boolean listed,
                     int sortOrder, String defaultValue, String values, String hint,
                     boolean fixed, boolean readOnly, boolean unique) {
        this.id = id;
        this.inode = inode;
        this.name = name;
        this.variable = variable;
        this.type = type;
        this.required = required;
        this.indexed = indexed;
        this.searchable = searchable;
        this.listed = listed;
        this.sortOrder = sortOrder;
        this.defaultValue = defaultValue;
        this.values = values;
        this.hint = hint;
        this.fixed = fixed;
        this.readOnly = readOnly;
        this.unique = unique;
    }

    public String getId() { return id; }
    public String getInode() { return inode; }
    public String getName() { return name; }
    public String getVariable() { return variable; }
    public String getType() { return type; }
    public boolean isRequired() { return required; }
    public boolean isIndexed() { return indexed; }
    public boolean isSearchable() { return searchable; }
    public boolean isListed() { return listed; }
    public int getSortOrder() { return sortOrder; }
    public String getDefaultValue() { return defaultValue; }
    public String getValues() { return values; }
    public String getHint() { return hint; }
    public boolean isFixed() { return fixed; }
    public boolean isReadOnly() { return readOnly; }
    public boolean isUnique() { return unique; }
}
