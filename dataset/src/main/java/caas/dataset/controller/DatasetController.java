package caas.dataset.controller;


import caas.dataset.entity.Dataset;
import caas.dataset.service.DatasetService;
import caas.dataset.util.BodyCheck;
import caas.dataset.util.Hdfs;
import caas.dataset.util.Response;
import caas.dataset.util.RestCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class DatasetController {
    @Autowired
    DatasetService datasetService;

    @RequestMapping(value = "/dataset", method = RequestMethod.GET)
    public Object get(@RequestParam(required = false) Integer id,
                      @RequestParam(required = false) Integer workspaceId) {
        Object result;
        if (id == null) {
            if (workspaceId == null) {
                result = datasetService.findAll();
            } else {
                if (!RestCall.workspaceExists(workspaceId)) {
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
                      @RequestParam("content") MultipartFile file) throws Exception {

        byte[] content = file.getBytes();

        if (!RestCall.workspaceExists(workspaceId)) {
            return new Response(404);
        }
        if (datasetService.existsByWorkspaceIdAndName(workspaceId, name)) {
            return new Response(406);
        }


        Object result = datasetService.create(workspaceId, name, false, content);
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
    public Object get(@RequestParam Integer id) throws Exception {
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

        Resource resource = new InputStreamResource(new ByteArrayInputStream(datasetService.getFile(id)));

        return ResponseEntity.ok().headers(headers).contentType(MediaType.MULTIPART_FORM_DATA).body(resource);
    }

}
