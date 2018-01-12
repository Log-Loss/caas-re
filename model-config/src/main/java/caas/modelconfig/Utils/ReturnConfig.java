package caas.modelconfig.Utils;

// this is the information which returned to frontend
public class ReturnConfig {
    public String modelId;
    public Long timeStamp;

    public ReturnConfig(String modelId, Long timeStamp) {
        this.modelId = modelId;
        this.timeStamp = timeStamp;
    }
}
