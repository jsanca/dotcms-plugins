Task 8 — Implement aggregated AI context endpoint

We already expose:

- GET /api/v1/ai/context/health
- GET /api/v1/ai/context/sample
- GET /api/v1/ai/context/sites
- GET /api/v1/ai/context/basetypes
- GET /api/v1/ai/context/content-types
- GET /api/v1/ai/context/content-types/{variableOrId}/fields
- GET /api/v1/ai/context/field-types

We also have:
- LayoutCapabilities
- AgentGuide

Now implement the main aggregated endpoint:

GET /api/v1/ai/context

Goal:
Return a single AiContextResponse containing all metadata an external AI agent needs to understand the current dotCMS instance.

AiContextResponse should include:

1. sites
   From DotCmsMetadataService.listSites()

2. baseTypes
   From DotCmsMetadataService.listBaseTypes()

3. contentTypes
   From DotCmsMetadataService.listContentTypes()

4. fieldTypes
   From DotCmsMetadataService.listFieldTypes()

5. layoutCapabilities
   From the static layout capabilities builder

6. agentGuide
   From the static agent guide builder

Rules:
- Read-only only.
- Do not add write endpoints.
- Do not create sites, content types, fields, pages, templates, containers, or content.
- Do not manually concatenate JSON.
- Keep DTOs simple and JSON-friendly.
- Keep existing endpoints working.
- Keep plugin packages private/internal in the OSGi manifest.
- Run mvn clean package.
- Verify the manifest does not import jsanca.dotcms.inspector.plugin.* packages.

After implementation, summarize:
1. Files changed.
2. Endpoint added.
3. Example JSON shape.
4. Build result.
5. Manifest verification result.