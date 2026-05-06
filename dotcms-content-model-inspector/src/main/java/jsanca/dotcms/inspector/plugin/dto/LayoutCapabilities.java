package jsanca.dotcms.inspector.plugin.dto;

public class LayoutCapabilities {

    private final LayoutSection fileBasedContainers;
    private final LayoutSection fileBasedTemplates;
    private final LayoutSection pages;
    private final LayoutSection content;

    public LayoutCapabilities(LayoutSection fileBasedContainers, LayoutSection fileBasedTemplates,
                              LayoutSection pages, LayoutSection content) {
        this.fileBasedContainers = fileBasedContainers;
        this.fileBasedTemplates = fileBasedTemplates;
        this.pages = pages;
        this.content = content;
    }

    public LayoutSection getFileBasedContainers() { return fileBasedContainers; }
    public LayoutSection getFileBasedTemplates() { return fileBasedTemplates; }
    public LayoutSection getPages() { return pages; }
    public LayoutSection getContent() { return content; }
}
