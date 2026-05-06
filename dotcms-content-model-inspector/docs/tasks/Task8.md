Task 7 — Add layout capabilities and agent guide metadata

We already expose runtime metadata for:
- sites
- base types
- content types
- fields
- field type definitions

Now add static guidance metadata for external AI agents about how to work with dotCMS layout structures.

Goal:
Enhance LayoutCapabilities and AgentGuide so the plugin can explain how an agent should approach templates, containers, pages, and content creation.

Add or update DTOs as needed.

LayoutCapabilities should include:

1. fileBasedContainers
    - supported: true
    - documentationUrl: https://dev.dotcms.com/docs/file-based-containers
    - recommendedPathPattern: /application/containers/{container-name}.vtl
    - description:
      Containers define areas where content can be placed. dotCMS supports file-based containers that can be represented as files in the site tree.
    - notes:
        - Prefer file-based containers when generating layout artifacts from an external design.
        - Containers should be created/uploaded as file assets when using the file-based approach.
        - Generated containers should be reusable and should avoid hardcoding content when possible.

2. fileBasedTemplates
    - supported: true
    - recommendedPathPattern: /application/templates/{template-name}.vtl
    - description:
      Templates define page layouts and reference containers.
    - notes:
        - Templates should compose containers into a page layout.
        - Generated templates should be based on the design structure.
        - Templates should not duplicate content model responsibilities.

3. pages
    - description:
      Pages are content based on the HTMLPAGE base type and use templates to render layout.
    - notes:
        - Pages should reference an existing or generated template.
        - Page content should be created/published through the appropriate dotCMS content/workflow APIs.

4. content
    - description:
      Content should be modeled using content types and fields.
    - notes:
        - Reuse existing content types when they fit the design.
        - Propose new content types only when existing ones do not match.
        - Use /field-types to select valid field types and supported properties.

AgentGuide should include a recommended high-level flow:

1. Inspect sites.
2. Inspect base types.
3. Inspect existing content types and fields.
4. Inspect field type definitions.
5. Decide which existing content types can be reused.
6. Propose missing content types and fields using valid field type definitions.
7. Create/upload file-based containers when layout regions are needed.
8. Create/upload file-based templates that compose containers.
9. Create pages using the selected template.
10. Create content using the appropriate content types.
11. Publish content/pages through workflow.

Also include known API areas to investigate later:
- File Assets API for file-based containers/templates.
- Content Type API for creating content types.
- Content API or Workflow API for creating content.
- Page API or Content API for creating pages.
- Workflow API for publishing.

Rules:
- This task only adds metadata/guidance.
- Do not implement creation endpoints.
- Do not mutate dotCMS data.
- Do not manually concatenate JSON.
- Keep existing endpoints working.
- Keep plugin packages private/internal in the OSGi manifest.
- Run mvn clean package.

After implementation, summarize:
1. DTOs changed.
2. Static guidance added.
3. Example JSON shape.
4. Build result.
5. Manifest verification result.