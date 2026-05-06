package jsanca.dotcms.inspector.plugin.dto;

import java.util.List;

public class SiteInfo {

    private final String id;
    private final String identifier;
    private final String name;
    private final String hostname;
    private final List<String> aliases;
    private final boolean defaultSite;
    private final boolean systemHost;
    private final boolean archived;

    public SiteInfo(String id, String identifier, String name, String hostname,
                    List<String> aliases, boolean defaultSite, boolean systemHost, boolean archived) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.hostname = hostname;
        this.aliases = aliases;
        this.defaultSite = defaultSite;
        this.systemHost = systemHost;
        this.archived = archived;
    }

    public String getId() { return id; }
    public String getIdentifier() { return identifier; }
    public String getName() { return name; }
    public String getHostname() { return hostname; }
    public List<String> getAliases() { return aliases; }
    public boolean isDefaultSite() { return defaultSite; }
    public boolean isSystemHost() { return systemHost; }
    public boolean isArchived() { return archived; }
}
