# dotcms-content-model-inspector

Read-only dotCMS OSGi plugin that exposes AI-friendly metadata about the current dotCMS instance.

## Purpose

Gives an external AI agent a single endpoint to understand the dotCMS content model: which sites exist, what base types are available, what content types and fields are defined, and what field types can be used to create new ones. Also includes static guidance about layout structures (file-based containers, templates, pages) and a recommended high-level flow for an agent to follow.

## Current scope

- Read-only introspection only.
- No creation or mutation of dotCMS data.
- No content type creation, page creation, content creation, template/container creation.

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/v1/ai/context` | Aggregated response — all metadata in one call |
| GET | `/api/v1/ai/context/health` | Plugin health check |
| GET | `/api/v1/ai/context/sites` | All sites/hosts |
| GET | `/api/v1/ai/context/basetypes` | dotCMS base content types (CONTENT, HTMLPAGE, FILEASSET, etc.) |
| GET | `/api/v1/ai/context/content-types` | All content types with their fields |
| GET | `/api/v1/ai/context/content-types/{variableOrId}/fields` | Fields for a specific content type |
| GET | `/api/v1/ai/context/field-types` | Field type definitions and their capabilities |
| GET | `/api/v1/ai/context/sample` | Hardcoded sample response showing the JSON shape |

### Main endpoint: `GET /api/v1/ai/context`

Returns a single `AiContextResponse` aggregating all of the above:

```json
{
  "sites": [ ... ],
  "baseTypes": [ ... ],
  "contentTypes": [ ... ],
  "fieldTypes": [ ... ],
  "layoutCapabilities": {
    "fileBasedContainers": {
      "supported": true,
      "documentationUrl": "https://dev.dotcms.com/docs/file-based-containers",
      "recommendedPathPattern": "/application/containers/{container-name}.vtl",
      "description": "...",
      "notes": [ ... ]
    },
    "fileBasedTemplates": { ... },
    "pages": { ... },
    "content": { ... }
  },
  "agentGuide": {
    "recommendedFlow": [
      "Inspect sites.",
      "Inspect base types.",
      "Inspect existing content types and fields.",
      "Inspect field type definitions.",
      "..."
    ],
    "apiAreasToInvestigate": [ ... ]
  }
}
```

## Build

Requires Java 21 and Maven 3.x.

```bash
mvn clean package
```

The OSGi bundle JAR is generated at `target/dotcms-content-model-inspector-1.0.0-SNAPSHOT.jar`.

## Deploy to dotCMS

### Option 1 — dotCMS UI

Upload the JAR via `CMS Admin → Plugins → Upload Plugin`.

To uninstall: `CMS Admin → Plugins → Undeploy`.

### Option 2 — Felix load folder

Copy the JAR directly to the Felix OSGi container:

```
dotCMS/felix/load/
```

dotCMS picks up new bundles from that folder automatically.

### Option 3 — Local deploy Maven profile

The project includes a `deploy-local-dotcms` profile that copies the JAR to a local Felix upload directory:

```bash
mvn clean verify -Pdeploy-local-dotcms \
  -Ddotcms.felix.upload.dir=/path/to/dotcms-felix/upload
```

Or using an environment variable:

```bash
export DOTCMS_FELIX_UPLOAD_DIR=/path/to/dotcms-felix/upload

mvn clean verify -Pdeploy-local-dotcms \
  -Ddotcms.felix.upload.dir="$DOTCMS_FELIX_UPLOAD_DIR"
```

The JAR is copied to the Felix upload folder; dotCMS processes it and moves it to the Felix load folder.

### Docker / containerized dotCMS

The Felix upload folder can be mounted as a local volume into the dotCMS container. Point `dotcms.felix.upload.dir` at the host path of that mounted volume so the Maven profile can write directly into it without entering the container.

Example `docker-compose` volume snippet:

```yaml
volumes:
  - ./dotcms-felix/upload:/srv/dotcms/felix/upload
```

Then deploy with:

```bash
mvn clean verify -Pdeploy-local-dotcms \
  -Ddotcms.felix.upload.dir=./dotcms-felix/upload
```

## OSGi notes

- `Import-Package: *` lets the Felix bnd tool resolve imports automatically from the dotCMS runtime.
- Plugin packages (`jsanca.dotcms.inspector.plugin.*`) are internal to the bundle and are not exported.
- The dotCMS runtime must export the packages this plugin uses. Verify them at `CMS Admin → Plugins → Exported Packages` or in `dotCMS/WEB-INF/felix/osgi-extra.conf`.

## Future work

- Content type creation guidance and API.
- File-based container and template creation.
- Page and content creation.
- Workflow publish support.
- More detailed agent recipes per use case.
