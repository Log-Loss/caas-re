package caas.modelconfig.Utils;

import java.util.HashMap;
import java.util.Map;

public class Layer {
    public String type;

    public String activation;
    public String weightInit;
    public String outputDim;

    public String filters;
    public String kernelSize;
    public String strides;
    public String padding;

    public String poolingType;

    public String dropoutRate;
    public String lossFunction;
    public String hiddenLayerWidth;
    public String hiddenLayerCount;
    public String sequenceLen;

    public String outputNum;


    public Map<String, String> config = new HashMap<>();

    public Layer() {

    }

    public Map<String, String> buildConfig() {
        this.config.put("activation", this.activation);
        this.config.put("weightInit", this.weightInit);
        this.config.put("outputDim", this.outputDim);
        this.config.put("filters", this.filters);
        this.config.put("kernelSize", this.kernelSize);
        this.config.put("strides", this.strides);
        this.config.put("padding", this.padding);
        this.config.put("poolingType", this.poolingType);
        this.config.put("dropoutRate", this.dropoutRate);
        this.config.put("lossFunction", this.lossFunction);
        this.config.put("hiddenLayerCount", this.hiddenLayerCount);
        this.config.put("hiddenLayerWidth", this.hiddenLayerWidth);
        this.config.put("sequenceLen", this.sequenceLen);
        this.config.put("outputNum", this.outputNum);

        return config;
    }
}
