Task 9 — Final README

Create/update README.md for dotcms-content-model-inspector.

Document:

1. Purpose
   This is a read-only dotCMS OSGi plugin that exposes AI-friendly metadata about the current dotCMS instance.

2. Current scope
    - Read-only metadata introspection.
    - No creation/mutation endpoints.
    - No content type creation.
    - No page/content/template/container creation yet.

3. Endpoints
    - GET /api/v1/ai/context/health
    - GET /api/v1/ai/context/sample
    - GET /api/v1/ai/context/sites
    - GET /api/v1/ai/context/basetypes
    - GET /api/v1/ai/context/content-types
    - GET /api/v1/ai/context/content-types/{variableOrId}/fields
    - GET /api/v1/ai/context/field-types
    - GET /api/v1/ai/context

4. Main endpoint
   Explain that GET /api/v1/ai/context aggregates:
    - sites
    - baseTypes
    - contentTypes
    - fieldTypes
    - layoutCapabilities
    - agentGuide

5. Build
   mvn clean package

6. Local deploy to dotCMS
   Document the deploy-local-dotcms profile:

   mvn clean verify -Pdeploy-local-dotcms \
   -Ddotcms.felix.upload.dir=/path/to/dotcms-felix/upload

7. Docker/local dotCMS note
   Explain that the Felix upload folder can be externalized as a local folder mounted into the dotCMS container.

8. Future work
    - Content type creation guidance/API.
    - File-based container/template creation.
    - Page/content creation.
    - Workflow publish support.
    - More detailed agent recipes.

Rules:
- Do not claim unimplemented write capabilities.
- Keep it concise but complete.
- Run mvn clean package after editing.