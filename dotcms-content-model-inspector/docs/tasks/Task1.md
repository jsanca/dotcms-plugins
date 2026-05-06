## Task 1 — Inspect project, no changes

```text
We are working on a generated dotCMS OSGi REST plugin project named dotcms-content-model-inspector.

Goal of the plugin:
Expose read-only, AI-friendly metadata about the current dotCMS instance so an external agent can understand sites, content types, fields, and layout capabilities.

Important:
Do not edit files yet.
Do not invent dotCMS APIs.
First inspect the project.

Please summarize:
1. Current package structure.
2. Current Activator class.
3. Current REST resource class.
4. Current Maven build configuration.
5. Any obvious issues before we start implementing the AI metadata context plugin.

After the summary, propose a short implementation plan for the first milestone only.
```

## Task 2 — Clean names and add health endpoint

```text
Milestone 1: Clean the generated REST plugin skeleton.

Please implement the following:

1. Rename the example REST resource to AiContextResource.
2. Keep the existing OSGi Activator pattern working.
3. Keep the current package structure unless there is an obvious generated-example naming issue.
4. Add a read-only health endpoint.

Expected endpoint:
GET /api/v1/ai/context/health

Expected JSON response:
{
  "status": "ok",
  "plugin": "dotcms-content-model-inspector"
}

Rules:
- Do not implement dotCMS metadata introspection yet.
- Do not add write operations.
- Do not invent dotCMS APIs.
- Keep the project compiling.

After changes, run:
mvn clean package

Then summarize:
1. Files changed.
2. Endpoint added.
3. Build result.
```

## Task 3 — Add DTO model and sample endpoint

```text
Milestone 2: Define the JSON shape for the AI context response.

Please add DTO classes for the future metadata response.

Create simple DTOs/POJOs for:

1. AiContextResponse
2. SiteInfo
3. ContentTypeInfo
4. FieldInfo
5. LayoutCapabilities
6. AgentGuide

For now, do not connect to dotCMS APIs.

Add a sample endpoint:
GET /api/v1/ai/context/sample

It should return a hardcoded AiContextResponse containing:
- One sample site.
- One sample content type with two sample fields.
- Layout capabilities mentioning file-based containers/templates.
- An agent guide with a recommended flow.

Rules:
- Keep this read-only.
- Keep DTOs simple and JSON-friendly.
- Do not use Lombok.
- Do not implement real dotCMS introspection yet.
- Keep the project compiling.

After changes, run:
mvn clean package

Then summarize:
1. DTOs created.
2. Endpoint added.
3. Sample JSON shape.
4. Build result.
```

## Task 4 — Implement site introspection

```text
Milestone 3: Implement read-only site introspection.

Please inspect the available dotCMS APIs/classes in the project dependencies and implement a service class named DotCmsMetadataService.

Add method:
listSites()

It should return List<SiteInfo>.

SiteInfo should include as many of these fields as are safely available:
- id
- identifier
- name
- hostname
- aliases
- defaultSite
- systemHost
- archived

Expose endpoint:
GET /api/v1/ai/context/sites

Rules:
- Read-only only.
- Do not invent dotCMS APIs.
- If the correct dotCMS API is unclear, leave a TODO and explain what needs confirmation.
- Do not implement content type introspection yet.
- Keep the project compiling.

After changes, run:
mvn clean package

Then summarize:
1. dotCMS APIs/classes used.
2. Files changed.
3. Any assumptions/TODOs.
4. Build result.
```

## Task 5 — Implement content type and field introspection

```text
Milestone 4: Implement read-only content type and field introspection.

Extend DotCmsMetadataService with:

1. listContentTypes()
2. findContentTypeFields(String variableOrId)

ContentTypeInfo should include as many as safely available:
- id
- inode
- name
- variable
- baseType
- description
- system
- fields

FieldInfo should include as many as safely available:
- id
- inode
- name
- variable
- type
- required
- indexed
- searchable
- listed
- sortOrder
- defaultValue
- values
- hint/helpText

Expose endpoints:
GET /api/v1/ai/context/content-types
GET /api/v1/ai/context/content-types/{variableOrId}/fields

Rules:
- Read-only only.
- Do not invent dotCMS APIs.
- If a dotCMS API is unclear, inspect dependencies or leave a TODO.
- Do not add creation/mutation endpoints.
- Keep the project compiling.

After changes, run:
mvn clean package

Then summarize:
1. dotCMS APIs/classes used.
2. Files changed.
3. Any assumptions/TODOs.
4. Build result.
```

## Task 6 — Main aggregated AI context endpoint

```text
Milestone 5: Implement the main aggregated AI context endpoint.

Expose:
GET /api/v1/ai/context

It should return AiContextResponse with:
- sites from DotCmsMetadataService.listSites()
- contentTypes from DotCmsMetadataService.listContentTypes()
- layoutCapabilities static metadata
- agentGuide static metadata

Rules:
- Read-only only.
- Do not create sites, pages, content, templates, containers, or workflows.
- Keep the endpoint safe as an introspection endpoint.
- Keep the project compiling.

After changes, run:
mvn clean package

Then summarize:
1. Endpoint behavior.
2. Files changed.
3. Build result.
```

## Task 7 — Add static agent capabilities and documentation notes

```text
Milestone 6: Add AI agent guidance metadata.

Enhance LayoutCapabilities and AgentGuide to include static guidance about:

1. File-based containers.
   Documentation URL:
   https://dev.dotcms.com/docs/file-based-containers

2. Templates.
   Explain that templates define page layout and reference containers.

3. Containers.
   Explain that containers define areas where content can be placed.

4. Recommended high-level flow:
   - Inspect available sites.
   - Inspect content types and fields.
   - Decide whether to reuse or create content types.
   - Create or upload containers/templates as files when appropriate.
   - Create pages.
   - Create content.
   - Publish through workflow.

5. Known API areas to investigate later:
   - File assets API for file-based containers/templates.
   - Content API or Workflow API for creating content.
   - Page API for creating pages.
   - Workflow API for publishing.

Rules:
- This task only adds guidance metadata.
- Do not implement creation APIs.
- Do not add write endpoints.
- Keep the project compiling.

After changes, run:
mvn clean package

Then summarize:
1. New metadata fields.
2. Files changed.
3. Build result.
```

## Task 8 — README for generated plugin

```text
Milestone 7: Add README documentation for this generated plugin.

Create or update README.md with:

1. What this plugin does.
2. What endpoints it exposes.
3. How to build it.
4. How to deploy the generated OSGi bundle to dotCMS.
5. Scope:
   - Read-only metadata introspection.
   - No creation/mutation of dotCMS data yet.
6. Future work:
   - Content type creation.
   - File-based containers/templates creation.
   - Page/content creation.
   - Workflow publishing.

Rules:
- Keep it concise.
- Do not claim features that are not implemented.
- Keep the project compiling.

After changes, summarize the README contents.
```


