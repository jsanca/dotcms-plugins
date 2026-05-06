package jsanca.dotcms.inspector.plugin.rest;

import jsanca.dotcms.inspector.plugin.dto.AgentGuide;
import jsanca.dotcms.inspector.plugin.dto.AiContextResponse;
import jsanca.dotcms.inspector.plugin.dto.BaseTypeInfo;
import jsanca.dotcms.inspector.plugin.dto.ContentTypeInfo;
import jsanca.dotcms.inspector.plugin.dto.FieldInfo;
import jsanca.dotcms.inspector.plugin.dto.FieldTypeDefinitionInfo;
import jsanca.dotcms.inspector.plugin.dto.LayoutCapabilities;
import jsanca.dotcms.inspector.plugin.dto.LayoutSection;
import jsanca.dotcms.inspector.plugin.dto.SiteInfo;
import jsanca.dotcms.inspector.plugin.service.DotCmsMetadataService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/v1/ai/context")
public class AiContextResource {

    private final DotCmsMetadataService metadataService = new DotCmsMetadataService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response context(@Context HttpServletRequest request) {
        final AiContextResponse response = new AiContextResponse(
                metadataService.listSites(),
                metadataService.listBaseTypes(),
                metadataService.listContentTypes(),
                metadataService.listFieldTypes(),
                buildLayoutCapabilities(),
                buildAgentGuide()
        );
        return Response.ok(response).build();
    }

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public Response health(@Context HttpServletRequest request) {
        return Response.ok("{\"status\":\"ok\",\"plugin\":\"dotcms-content-model-inspector\"}")
                .build();
    }

    @GET
    @Path("/sites")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sites(@Context HttpServletRequest request) {
        return Response.ok(metadataService.listSites()).build();
    }

    @GET
    @Path("/basetypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response basetypes(@Context HttpServletRequest request) {
        return Response.ok(metadataService.listBaseTypes()).build();
    }

    @GET
    @Path("/field-types")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fieldTypes(@Context HttpServletRequest request) {
        return Response.ok(metadataService.listFieldTypes()).build();
    }

    @GET
    @Path("/content-types")
    @Produces(MediaType.APPLICATION_JSON)
    public Response contentTypes(@Context HttpServletRequest request) {
        return Response.ok(metadataService.listContentTypes()).build();
    }

    @GET
    @Path("/content-types/{variableOrId}/fields")
    @Produces(MediaType.APPLICATION_JSON)
    public Response contentTypeFields(@Context HttpServletRequest request,
                                      @PathParam("variableOrId") String variableOrId) {
        return Response.ok(metadataService.findFields(variableOrId)).build();
    }

    @GET
    @Path("/sample")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sample(@Context HttpServletRequest request) {
        return Response.ok(buildSampleResponse()).build();
    }

    static LayoutCapabilities buildLayoutCapabilities() {
        LayoutSection fileBasedContainers = new LayoutSection(
                true,
                "https://dev.dotcms.com/docs/file-based-containers",
                "/application/containers/{container-name}.vtl",
                "Containers define areas where content can be placed. dotCMS supports file-based containers " +
                "that can be represented as files in the site tree.",
                Arrays.asList(
                        "Prefer file-based containers when generating layout artifacts from an external design.",
                        "Containers should be created/uploaded as file assets when using the file-based approach.",
                        "Generated containers should be reusable and should avoid hardcoding content when possible."
                )
        );

        LayoutSection fileBasedTemplates = new LayoutSection(
                true,
                null,
                "/application/templates/{template-name}.vtl",
                "Templates define page layouts and reference containers.",
                Arrays.asList(
                        "Templates should compose containers into a page layout.",
                        "Generated templates should be based on the design structure.",
                        "Templates should not duplicate content model responsibilities."
                )
        );

        LayoutSection pages = new LayoutSection(
                null,
                null,
                null,
                "Pages are content based on the HTMLPAGE base type and use templates to render layout.",
                Arrays.asList(
                        "Pages should reference an existing or generated template.",
                        "Page content should be created/published through the appropriate dotCMS content/workflow APIs."
                )
        );

        LayoutSection content = new LayoutSection(
                null,
                null,
                null,
                "Content should be modeled using content types and fields.",
                Arrays.asList(
                        "Reuse existing content types when they fit the design.",
                        "Propose new content types only when existing ones do not match.",
                        "Use /field-types to select valid field types and supported properties."
                )
        );

        return new LayoutCapabilities(fileBasedContainers, fileBasedTemplates, pages, content);
    }

    static AgentGuide buildAgentGuide() {
        return new AgentGuide(
                Arrays.asList(
                        "Inspect sites.",
                        "Inspect base types.",
                        "Inspect existing content types and fields.",
                        "Inspect field type definitions.",
                        "Decide which existing content types can be reused.",
                        "Propose missing content types and fields using valid field type definitions.",
                        "Create/upload file-based containers when layout regions are needed.",
                        "Create/upload file-based templates that compose containers.",
                        "Create pages using the selected template.",
                        "Create content using the appropriate content types.",
                        "Publish content/pages through workflow."
                ),
                Arrays.asList(
                        "File Assets API for file-based containers/templates.",
                        "Content Type API for creating content types.",
                        "Content API or Workflow API for creating content.",
                        "Page API or Content API for creating pages.",
                        "Workflow API for publishing."
                )
        );
    }

    private AiContextResponse buildSampleResponse() {
        List<SiteInfo> sites = metadataService.listSites();
        List<BaseTypeInfo> baseTypes = metadataService.listBaseTypes();
        List<FieldTypeDefinitionInfo> fieldTypes = metadataService.listFieldTypes();

        List<FieldInfo> fields = Arrays.asList(
                new FieldInfo(
                        "f1a2b3c4-d5e6-7890-abcd-ef1234567890",
                        "f1a2b3c4-d5e6-7890-abcd-ef1234567890",
                        "Title", "title", "TextField",
                        true, true, true, true, 0, null, null,
                        "The blog post title", false, false, false
                ),
                new FieldInfo(
                        "a2b3c4d5-e6f7-8901-bcde-f12345678901",
                        "a2b3c4d5-e6f7-8901-bcde-f12345678901",
                        "Body", "body", "WysiwygField",
                        false, false, false, false, 1, null, null,
                        "The blog post body content", false, false, false
                )
        );

        List<ContentTypeInfo> contentTypes = Collections.singletonList(
                new ContentTypeInfo(
                        "c1d2e3f4-a5b6-7890-cdef-123456789012",
                        "c1d2e3f4-a5b6-7890-cdef-123456789012",
                        "Blog", "Blog", "CONTENT", "A sample blog content type", false, fields
                )
        );

        return new AiContextResponse(sites, baseTypes, contentTypes, fieldTypes,
                buildLayoutCapabilities(), buildAgentGuide());
    }
}
