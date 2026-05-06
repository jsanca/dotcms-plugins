Task 6 — Expose dotCMS Field Type Definitions for AI Agents

We already expose:
- /health
- /sample
- /sites
- /basetypes
- /content-types
- /content-types/{variableOrId}/fields

Before building the aggregated /ai/context endpoint, we need to expose the field type definitions/capabilities.

Goal:
Add endpoint:

GET /api/v1/ai/context/field-types

This endpoint should return metadata about the dotCMS Field subclasses that can be used to create content type fields.

Use the Field base class and known Field subclasses from com.dotcms.contenttype.model.field.

Return a DTO named FieldTypeDefinitionInfo with as many of these properties as safely available:

- name
- className
- legacyName, if safely available
- labelKey
- helpTextKey
- dataType
- acceptedDataTypes
- fieldVariableKeys
- contentTypeProperties
- commonProperties
- supportsValues
- supportsDefaultValue
- supportsRegexCheck
- supportsHint
- supportsRequired
- supportsIndexed
- supportsListed
- supportsSearchable
- supportsUnique
- supportsReadOnly
- supportsFixed

Important:
The goal is to give an external AI agent enough metadata to generically understand what kinds of fields dotCMS can create.

Rules:
- Read-only only.
- Do not create fields.
- Do not create content types.
- Do not mutate dotCMS data.
- Do not manually concatenate JSON.
- Keep DTOs simple and JSON-friendly.
- Keep existing endpoints working.
- Keep plugin packages private/internal in the OSGi manifest.
- Run mvn clean package.

Implementation note:
If instantiating every Field subclass is awkward because some are immutable/generated classes or require builders, use an explicit registry/list of supported field classes and extract what can be safely extracted. If a value cannot be obtained safely, return null or an empty collection and mention it in the summary.

After implementation, summarize:
1. Field classes covered.
2. DTOs created/changed.
3. Endpoint added.
4. Example JSON shape.
5. Any metadata that could not be extracted safely.
6. Build result.