package jsanca.dotcms.inspector.plugin.dto;

import java.util.List;

public class AgentGuide {

    private final List<String> recommendedFlow;
    private final List<String> apiAreasToInvestigate;

    public AgentGuide(List<String> recommendedFlow, List<String> apiAreasToInvestigate) {
        this.recommendedFlow = recommendedFlow;
        this.apiAreasToInvestigate = apiAreasToInvestigate;
    }

    public List<String> getRecommendedFlow() { return recommendedFlow; }
    public List<String> getApiAreasToInvestigate() { return apiAreasToInvestigate; }
}
