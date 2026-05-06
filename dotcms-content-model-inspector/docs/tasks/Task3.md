Milestone 2: Define the JSON shape for the AI context response.

Please add DTO classes for the future metadata response.

Create simple JSON-friendly POJOs for:

1. AiContextResponse
2. SiteInfo
3. ContentTypeInfo
4. FieldInfo
5. LayoutCapabilities
6. AgentGuide

For now, do not connect to real dotCMS APIs.

Add a sample endpoint:

GET /api/v1/ai/context/sample

It should return a hardcoded AiContextResponse containing:
- One sample site.
- One sample content type with two sample fields.
- Layout capabilities mentioning file-based containers/templates.
- An agent guide with a recommended flow.

Rules:
- Keep this read-only.
- Keep DTOs simple.
- Do not use Lombok.
- Do not implement real dotCMS introspection yet.
- Do not add write endpoints.
- Keep using the existing REST/resource style from the plugin.
- Keep the project compiling.

Important:
Use proper JSON serialization if the existing dotCMS/JAX-RS stack supports returning POJOs directly.
If not, use the least invasive approach and explain the tradeoff.

After changes, run:

mvn clean package

Then summarize:
1. DTOs created.
2. Endpoint added.
3. Sample JSON shape.
4. Build result.