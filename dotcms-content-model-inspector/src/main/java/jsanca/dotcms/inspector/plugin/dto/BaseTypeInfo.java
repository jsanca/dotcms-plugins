package jsanca.dotcms.inspector.plugin.dto;

public class BaseTypeInfo {

    private final int type;
    private final String name;
    private final String alternateName;
    private final String immutableClass;

    public BaseTypeInfo(int type, String name, String alternateName, String immutableClass) {
        this.type = type;
        this.name = name;
        this.alternateName = alternateName;
        this.immutableClass = immutableClass;
    }

    public int getType() { return type; }
    public String getName() { return name; }
    public String getAlternateName() { return alternateName; }
    public String getImmutableClass() { return immutableClass; }
}
