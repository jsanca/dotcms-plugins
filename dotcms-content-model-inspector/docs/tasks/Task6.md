Task 5 — Expose real dotCMS Content Types and Fields

We already have:
- GET /api/v1/ai/context/health
- GET /api/v1/ai/context/sample
- GET /api/v1/ai/context/sites
- GET /api/v1/ai/context/basetypes

Now implement real read-only content type and field introspection.

Goal:
Expose the existing dotCMS content model so an AI agent can understand which Content Types exist and which fields each one has.

Add endpoints:

GET /api/v1/ai/context/content-types
GET /api/v1/ai/context/content-types/{variableOrId}/fields

Extend DotCmsMetadataService with:

List<ContentTypeInfo> listContentTypes()
List<FieldInfo> findFields(String variableOrId)

ContentTypeInfo should include:
- id
- inode, if available
- name
- variable
- baseType
- description
- system
- fields

FieldInfo should include:
- id
- inode, if available
- name
- variable
- type
- required
- indexed
- listed
- searchable, if available
- sortOrder
- defaultValue
- values
- hint/helpText, if available
- fixed, if available
- readOnly, if available
- unique, if available

Important design rule:
For GET /content-types, include fields only if it is not too expensive or awkward.
If fields are expensive or make the response too large, return content type summaries there and expose full fields through /content-types/{variableOrId}/fields.

Implementation rules:
- Read-only only.
- Do not create content types.
- Do not create fields.
- Do not mutate dotCMS data.
- Use APILocator.systemUser().
- Keep existing endpoints working.
- Keep plugin packages private/internal in the OSGi manifest.
- Do not introduce Lombok.
- Do not manually concatenate JSON.
- Run mvn clean package.

After implementation, summarize:
1. dotCMS APIs/classes used.
2. Files changed.
3. Endpoints added.
4. Example JSON shape.
5. Any fields that were unavailable or intentionally omitted.
6. Build result.