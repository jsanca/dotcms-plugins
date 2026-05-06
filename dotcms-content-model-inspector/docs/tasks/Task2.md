Proceed with Task 2.

One correction:
Before choosing the JAX-RS @Path value, check how dotCMS mounts plugin REST resources in this blueprint.

The desired final endpoint is:

GET /api/v1/ai/context/health

Avoid creating a duplicated path such as:

/api/v1/v1/ai/context/health

Implementation requirements:
1. Rename ExampleResource.java to AiContextResource.java.
2. Update Activator.java to register AiContextResource.class instead of ExampleResource.class.
3. Remove POST, PUT, and auth/example endpoints.
4. Add a read-only health endpoint returning:
   {
   "status": "ok",
   "plugin": "dotcms-content-model-inspector"
   }
5. Keep using the dotCMS WebResource pattern if needed.
6. Do not implement DTOs yet.
7. Do not implement dotCMS metadata introspection yet.
8. Run mvn clean package.

After the changes, summarize:
- Files changed.
- Final endpoint path.
- Build result.