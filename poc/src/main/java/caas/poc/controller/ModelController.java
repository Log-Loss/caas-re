package caas.poc.controller;


import caas.poc.service.DatasetService;
import caas.poc.service.ModelService;
import caas.poc.service.WorkspaceService;
import caas.poc.util.BodyCheck;
import caas.poc.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ModelController {
    @Autowired
    ModelService modelService;

    @Autowired
    DatasetService datasetService;

    @Autowired
    WorkspaceService workspaceService;

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public Object get(@RequestParam(required = false) Integer id,
                      @RequestParam(required = false) Integer workspaceId) {
        Object result;
        if (id == null) {
            if (workspaceId == null) {
                result = modelService.findAll();
            } else {
                if (!workspaceService.exists(workspaceId)) {
                    return new Response(404);
                }
                result = modelService.findAllByWorkspaceId(workspaceId);
            }
        } else {
            if (!modelService.exists(id)) {
                return new Response(404);
            }
            result = modelService.findOne(id);
        }

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/model", method = RequestMethod.POST)
    public Object post(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "workspaceId", "name")) {
            return new Response(412);
        }
        Integer workspaceId = Integer.valueOf(body.get("workspaceId"));
        String name = body.get("name");

        if (modelService.existsByWorkspaceIdAndName(workspaceId, name)) {
            return new Response(406);
        }

        Object result = modelService.create(workspaceId, name);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/model", method = RequestMethod.PUT)
    public Object put(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "id")) {
            return new Response(412);
        }
        Integer id = Integer.parseInt(body.get("id"));
        String name = body.get("name");
        String config = body.get("config");
        String datasetName = body.get("datasetName");
        String type = body.get("type");
        if (!modelService.exists(id)) {
            return new Response(404);
        }

        Object result = modelService.update(id, name, config, datasetName, type);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/model", method = RequestMethod.DELETE)
    public Object delete(Integer id) {
        if (!modelService.exists(id)) {
            return new Response(404);
        }
        modelService.remove(id);
        return new Response(200, "OK", "success");
    }
}
