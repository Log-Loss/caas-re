package caas.poc.controller;


import caas.poc.service.UserService;
import caas.poc.service.WorkspaceService;
import caas.poc.util.BodyCheck;
import caas.poc.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WorkspaceController {
    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/workspace", method = RequestMethod.GET)
    public Object get(@RequestParam(required = false) Integer id,
                      @RequestParam(required = false) Integer userId) {
        Object result;
        if (id == null) {
            if (userId == null) {
                result = workspaceService.findAll();
            } else {
                if (!userService.exists(userId)) {
                    return new Response(404);
                }
                result = workspaceService.findByUserId(userId);
            }
        } else {
            if (!workspaceService.exists(id)) {
                return new Response(404);
            }
            result = workspaceService.findOne(id);
        }

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/workspace", method = RequestMethod.POST)
    public Object post(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "userId", "name")) {
            return new Response(412);
        }
        Integer userId = Integer.parseInt(body.get("userId"));
        String name = body.get("name");

        if (!userService.exists(userId)) {
            return new Response(404);
        }
        if (workspaceService.existsUserIdAndName(userId, name)) {
            return new Response(406);
        }

        Object result = workspaceService.create(userId, name);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/workspace", method = RequestMethod.PUT)
    public Object put(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "id", "name")) {
            return new Response(412);
        }
        Integer id = Integer.parseInt(body.get("id"));
        String name = body.get("name");
        if (!workspaceService.exists(id)) {
            return new Response(404);
        }

        Object result = workspaceService.update(id, name);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/workspace", method = RequestMethod.DELETE)
    public Object delete(Integer id) {
        if (!workspaceService.exists(id)) {
            return new Response(404);
        }

        workspaceService.remove(id);
        return new Response(200, "OK", "success");
    }
}
