package caas.poc.controller;


import caas.poc.entity.Dataset;
import caas.poc.service.DatasetService;
import caas.poc.service.WorkspaceService;
import caas.poc.util.BodyCheck;
import caas.poc.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
public class DatasetController {
    @Autowired
    DatasetService datasetService;

    @Autowired
    WorkspaceService workspaceService;

    @RequestMapping(value = "/dataset", method = RequestMethod.GET)
    public Object get(@RequestParam(required = false) Integer id,
                      @RequestParam(required = false) Integer workspaceId) {
        Object result;
        if (id == null) {
            if (workspaceId == null) {
                result = datasetService.findAll();
            } else {
                if (!workspaceService.exists(workspaceId)) {
                    return new Response(404);
                }
                List<Dataset> datasets = datasetService.findAllByIsWorkspaceId(workspaceId);
                datasets.addAll(datasetService.findAllByIsPublic(true));
                result = datasets;
            }
        } else {
            if (!datasetService.exist(id)) {
                return new Response(404);
            }
            result = datasetService.findOne(id);
        }

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/dataset", method = RequestMethod.POST)
    public Object put(@RequestParam("workspaceId") Integer workspaceId,
                      @RequestParam("name") String name,
                      @RequestParam("content") MultipartFile file) throws IOException {

        byte[] content = file.getBytes();

        if (!workspaceService.exists(workspaceId)) {
            return new Response(404);
        }
        if (datasetService.existsByWorkspaceIdAndName(workspaceId, name)) {
            return new Response(406);
        }

        Object result = datasetService.create(workspaceId, name, content, false);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/dataset", method = RequestMethod.PUT)
    public Object put(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "id", "name")) {
            return new Response(412);
        }
        Integer id = Integer.parseInt(body.get("id"));
        String name = body.get("name");
        if (!datasetService.exist(id)) {
            return new Response(404);
        }

        Object result = datasetService.setName(id, name);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/dataset", method = RequestMethod.DELETE)
    public Object delete(Integer id) {
        if (!datasetService.exist(id)) {
            return new Response(404);
        }
        datasetService.remove(id);
        return new Response(200, "OK", "success");
    }

    @RequestMapping(value = "/dataset/download", method = RequestMethod.GET)
    public Object get(@RequestParam Integer id) throws UnsupportedEncodingException {
        Object result;

        if (!datasetService.exist(id)) {
            return new Response(404);
        }

        Dataset dataset = datasetService.findOne(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("charset", "utf-8");
        String filename = URLEncoder.encode(dataset.name, "UTF-8");
        headers.add("Content-Disposition", "attachment;filename=\"" + filename + "\"");

//        Resource resource = new InputStreamResource(new ByteArrayInputStream(dataset.content));

//        return ResponseEntity.ok().headers(headers).contentType(MediaType.MULTIPART_FORM_DATA).body(resource);
        return null;
    }
}
