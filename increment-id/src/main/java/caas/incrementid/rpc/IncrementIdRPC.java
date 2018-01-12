package caas.incrementid.rpc;


import caas.incrementid.service.IncrementIdService;
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
public class IncrementIdRPC {
    @Autowired
    IncrementIdService incrementIdService;

    @RequestMapping(value = "/rpc/increamentid", method = RequestMethod.POST)
    public Object get(@RequestBody Map<String, String> body) {
        return incrementIdService.get(body.get("name"));
    }
}
