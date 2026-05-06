package jsanca.dotcms.inspector.plugin.dto;

import java.util.List;

public class LayoutSection {

    private final Boolean supported;
    private final String documentationUrl;
    private final String recommendedPathPattern;
    private final String description;
    private final List<String> notes;

    public LayoutSection(Boolean supported, String documentationUrl,
                         String recommendedPathPattern, String description, List<String> notes) {
        this.supported = supported;
        this.documentationUrl = documentationUrl;
        this.recommendedPathPattern = recommendedPathPattern;
        this.description = description;
        this.notes = notes;
    }

    public Boolean getSupported() { return supported; }
    public String getDocumentationUrl() { return documentationUrl; }
    public String getRecommendedPathPattern() { return recommendedPathPattern; }
    public String getDescription() { return description; }
    public List<String> getNotes() { return notes; }
}
