package caas.poc.rpc;

import caas.poc.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WorkspaceRPC {
    @Autowired
    WorkspaceService workspaceService;

    @RequestMapping(value = "/rpc/workspace/exists", method = RequestMethod.POST)
    public Object exists(@RequestBody Map<String, String> body) {
        return workspaceService.exists(Integer.valueOf(body.get("id")));
    }
}