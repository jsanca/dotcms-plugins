Milestone 4: Implement read-only content type and field introspection.

The site introspection endpoint is implemented.

Now extend DotCmsMetadataService with:

1. List<ContentTypeInfo> listContentTypes()
2. List<FieldInfo> findContentTypeFields(String variableOrId)

Expose endpoints:

GET /api/v1/ai/context/content-types
GET /api/v1/ai/context/content-types/{variableOrId}/fields

ContentTypeInfo should include as many as safely available from dotCMS APIs:
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

Rules:
- Read-only only.
- Do not add write endpoints.
- Do not create content types or fields.
- Do not invent dotCMS APIs.
- Inspect available dotCMS classes/APIs from the dependency.
- If an API is unclear, leave a TODO and explain it.
- Keep health, sample, and sites endpoints working.
- Keep the project compiling.
- Keep jsanca.dotcms.inspector.plugin.* private/internal in the OSGi manifest.

After changes:
1. Run mvn clean package.
2. Inspect the manifest and verify jsanca.dotcms.inspector.plugin.* is not imported.
3. Summarize:
    - dotCMS APIs/classes used.
    - Files changed.
    - Endpoints added.
    - Any assumptions/TODOs.
    - Build result.