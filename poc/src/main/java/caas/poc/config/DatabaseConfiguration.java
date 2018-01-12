package caas.poc.config;

import caas.poc.service.DatasetService;
import caas.poc.service.ModelService;
import caas.poc.service.UserService;
import caas.poc.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    UserService userService;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    DatasetService datasetService;

    @Autowired
    ModelService modelService;

    @EventListener(ApplicationReadyEvent.class)
    public void config() throws IOException {

        workspaceService.removeAll();
        datasetService.removeAll();
        userService.removeAll();
        modelService.removeAll();

        userService.create("user@email.com", "password");
        userService.create("user1@email.com", "password1");
        userService.create("user2@email.com", "password2");

        workspaceService.create(1, "1workspace");
        workspaceService.create(1, "1workspace1");

        workspaceService.create(2, "2workspace");
        workspaceService.create(2, "2workspace1");

        modelService.create(1, "1model");
        modelService.create(1, "1model1");
        modelService.create(1, "1model2");
        modelService.create(2, "2model");
        modelService.create(2, "2model1");
        modelService.create(2, "2model2");
        modelService.create(3, "3model");
        modelService.create(3, "3model1");
        modelService.create(3, "3model2");

        modelService.update(1, "1model", "{\"userId\":\"licor\",\"dataset\":\"iris\",\"globalVariable\":{\"iteration\":\"1\",\"lr\":\"0.01\",\"l2\":\"0.0002\"},\"layers\":[{\"outputDim\":\"1000\",\"activation\":\"relu\",\"weightInit\":\"xavier\",\"type\":\"dense\"},{\"outputDim\":\"500\",\"activation\":\"relu\",\"weightInit\":\"xavier\",\"type\":\"dense\"},{\"outputNum\":\"3\",\"activation\":\"softmax\",\"lossFunction\":\"neg\",\"type\":\"output\"}]}", "iris","dense");

        Resource resource = new ClassPathResource("datasets/t10k-labels-idx1-ubyte");
        byte[] content = Files.readAllBytes(Paths.get(resource.getFile().getPath()));
        datasetService.create(1, "1dataset", content, false);
        datasetService.create(1, "1dataset1", content, false);
        datasetService.create(1, "1dataset2", content, false);
        datasetService.create(2, "2dataset", content, false);
        datasetService.create(2, "2dataset1", content, false);
        datasetService.create(2, "2dataset2", content, false);

        datasetService.create(0, "dataset", content, true);
        datasetService.create(0, "dataset1", content, true);
        datasetService.create(0, "dataset2", content, true);
        datasetService.create(0, "dataset3", content, true);

    }
}