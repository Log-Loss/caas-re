package caas.modelconfig;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ModelRepository extends MongoRepository<ModelConfig, String> {

    ModelConfig getModelConfigByModelId(String id);

    List<ModelConfig> getModelConfigsByUserId(String id);
}
