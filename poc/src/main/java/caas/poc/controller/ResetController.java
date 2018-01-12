package caas.poc.controller;

import caas.poc.service.DatasetService;
import caas.poc.service.ModelService;
import caas.poc.service.UserService;
import caas.poc.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ResetController {
    @Autowired
    UserService userService;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    DatasetService datasetService;

    @Autowired
    ModelService modelService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public void config() throws IOException {

        workspaceService.removeAll();
        datasetService.removeAll();
        userService.removeAll();
        modelService.removeAll();

        Integer userId = userService.create("user@email.com", "password").id;
        userService.create("user1@email.com", "password1");
        userService.create("user2@email.com", "password2");

        Integer workspaceId = workspaceService.create(userId, "1workspace").id;
        workspaceService.create(userId, "1workspace1");

        workspaceService.create(userId + 1, "2workspace");
        workspaceService.create(userId + 1, "2workspace1");


        modelService.create(workspaceId, "1model");
        modelService.create(workspaceId, "1model1");
        modelService.create(workspaceId, "1model2");
        modelService.create(workspaceId + 1, "2model");
        modelService.create(workspaceId + 1, "2model1");
        modelService.create(workspaceId + 1, "2model2");
        modelService.create(workspaceId + 2, "3model");
        modelService.create(workspaceId + 2, "3model1");
        modelService.create(workspaceId + 2, "3model2");


        Resource resource = new ClassPathResource("datasets/t10k-labels-idx1-ubyte");
        byte[] content = Files.readAllBytes(Paths.get(resource.getFile().getPath()));
        datasetService.create(workspaceId, "1dataset", content, false);
        datasetService.create(workspaceId, "1dataset1", content, false);
        datasetService.create(workspaceId, "1dataset2", content, false);
        datasetService.create(workspaceId + 1, "2dataset", content, false);
        datasetService.create(workspaceId + 1, "2dataset1", content, false);
        datasetService.create(workspaceId + 1, "2dataset2", content, false);

        datasetService.create(0, "dataset", content, true);
        datasetService.create(0, "dataset1", content, true);
        datasetService.create(0, "dataset2", content, true);
        datasetService.create(0, "dataset3", content, true);

    }
}