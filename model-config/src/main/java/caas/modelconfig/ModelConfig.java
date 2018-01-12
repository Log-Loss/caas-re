package caas.modelconfig;

import caas.modelconfig.Utils.Utils;
import org.springframework.data.annotation.Id;

public class ModelConfig {

    @Id
    private String id;
    private String modelId;
    private String userId;
    private Long timeStamp;

    private String configuration;

    public ModelConfig() {
        this.modelId = Utils.generateRandomId();
        this.timeStamp = Utils.getCurrentTime();
    }

    public ModelConfig(String userId, String configuration) {
        this.modelId = Utils.generateRandomId();
        this.timeStamp = Utils.getCurrentTime();
        this.userId = userId;
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
        this.timeStamp = Utils.getCurrentTime();
    }
}
