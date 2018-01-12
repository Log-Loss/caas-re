package caas.poc.controller;


import caas.poc.entity.Model;
import caas.poc.service.DatasetService;
import caas.poc.service.JobService;
import caas.poc.service.ModelService;
import caas.poc.service.WorkspaceService;
import caas.poc.util.BodyCheck;
import caas.poc.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class JobController {
    @Autowired
    ModelService modelService;

    @Autowired
    DatasetService datasetService;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    JobService jobService;

    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public Object get(@RequestParam(required = false) Integer id,
                      @RequestParam(required = false) Integer modelId) {
        Object result;
        if (id == null) {
            if (modelId == null) {
                result = jobService.findAll();
            } else {
                if (!modelService.exists(modelId)) {
                    return new Response(404);
                }
                result = jobService.findAllByModelId(modelId);
            }
        } else {
            if (!jobService.exists(id)) {
                return new Response(404);
            }
            result = jobService.findOne(id);
        }

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public Object post(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "modelId", "epochs", "batchSize")) {
            return new Response(412);
        }
        Integer modelId = Integer.valueOf(body.get("modelId"));
        Integer epochs = Integer.valueOf(body.get("epochs"));
        Integer batchSize = Integer.valueOf(body.get("batchSize"));

        Model model = modelService.findOne(modelId);

        Object result = jobService.create(model.id, model.datasetId, model.config, model.type, model.datasetName, epochs, batchSize);

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/job/start", method = RequestMethod.POST)
    public Object post(Integer id) throws Exception {
        if (!jobService.exists(id)) {
            return new Response(404);
        }
        Object result = jobService.start(id);

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/job", method = RequestMethod.PUT)
    public Object put(@RequestBody Map<String, String> body) throws Exception {
        Integer id = Integer.valueOf(body.get("id"));
        Integer epochs = Integer.valueOf(body.get("epochs"));
        Integer batchSize = Integer.valueOf(body.get("batchSize"));

        if (!jobService.exists(id)) {
            return new Response(404);
        }
        Object result = jobService.update(id, epochs, batchSize);

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/job", method = RequestMethod.DELETE)
    public Object delete(Integer id) {
        if (!jobService.exists(id)) {
            return new Response(404);
        }
        jobService.remove(id);
        return new Response(200, "OK", "success");
    }

}
