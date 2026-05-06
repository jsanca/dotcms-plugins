Milestone 3: Implement read-only site introspection.

The plugin is now running successfully in dotCMS.

Current validated endpoints:
- GET /api/v1/ai/context/health
- GET /api/v1/ai/context/sample

Now implement real site introspection.

Goal:
Add a service class named DotCmsMetadataService.

Implement:
List<SiteInfo> listSites()

Expose endpoint:
GET /api/v1/ai/context/sites

SiteInfo should include as many of these fields as are safely available from dotCMS APIs:
- id
- identifier
- name
- hostname
- aliases
- defaultSite
- systemHost
- archived

Rules:
- Read-only only.
- Do not implement content type introspection yet.
- Do not add write endpoints.
- Do not invent dotCMS APIs.
- Inspect available dotCMS classes/APIs from the project dependencies.
- If a field is unclear or not available, leave it null/default and mention it in the summary.
- Keep the existing health and sample endpoints working.
- Keep the project compiling.

After changes:
1. Run mvn clean package.
2. Inspect the manifest and verify jsanca.dotcms.inspector.plugin.* is not imported.
3. Summarize:
    - dotCMS APIs/classes used.
    - Files changed.
    - Endpoint added.
    - Any assumptions/TODOs.
    - Build result.