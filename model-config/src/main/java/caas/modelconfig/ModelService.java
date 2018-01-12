package caas.modelconfig;

import caas.modelconfig.Convolution.ConvConfig;
import caas.modelconfig.Convolution.ConvModel;
import caas.modelconfig.Dense.*;
import caas.modelconfig.Recurrent.RnnConfig;
import caas.modelconfig.Recurrent.RnnModel;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public ModelConfig buildDenseModel(DenseConfig config) {
        MultiLayerConfiguration configuration = new DenseModel(config).getConfig();

        return new ModelConfig(config.userId, configuration.toJson());
    }

    public ModelConfig buildConvModel(ConvConfig config) {
        MultiLayerConfiguration configuration = new ConvModel(config).getConfig();

        return new ModelConfig(config.userId, configuration.toJson());
    }

    public ModelConfig buildRnnModel(RnnConfig config) {
        MultiLayerConfiguration configuration = new RnnModel(config).getConfig();

        return new ModelConfig(config.userId, configuration.toJson());
    }

    public ModelConfig getModelById(String id) {
        return modelRepository.getModelConfigByModelId(id);
    }

    public List<ModelConfig> getModelsByUserId(String id) {
        return modelRepository.getModelConfigsByUserId(id);
    }
}
