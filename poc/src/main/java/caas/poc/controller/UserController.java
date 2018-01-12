package caas.poc.controller;

import caas.poc.service.UserService;
import caas.poc.util.BodyCheck;
import caas.poc.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object get(@RequestParam(required = false) Integer id) {
        Object result;
        if (id == null) {
            result = userService.findAll();
        } else {
            if (!userService.exists(id)) {
                return new Response(404);
            }
            result = userService.findOne(id);
        }

        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Object post(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "email", "password")) {
            return new Response(412);
        }
        String email = body.get("email");
        String password = body.get("password");

        if (userService.existsEmail(email)) {
            return new Response(406);
        }

        Object result = userService.create(email, password);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Object put(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "id", "email", "password")) {
            return new Response(412);
        }
        Integer id = Integer.parseInt(body.get("id"));
        String email = body.get("email");
        String password = body.get("password");

        if (!userService.exists(id)) {
            return new Response(404);
        }

        Object result = userService.update(id, email, password);
        return new Response(200, "OK", result);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public Object delete(Integer id) {
        if (!userService.exists(id)) {
            return new Response(404);
        }

        userService.remove(id);
        return new Response(200, "OK", "success");
    }

    @RequestMapping(value = "/user/session", method = RequestMethod.POST)
    public Object auth(@RequestBody Map<String, String> body) {
        if (!BodyCheck.check(body, "email", "password")) {
            return new Response(412);
        }
        String email = body.get("email");
        String password = body.get("password");

        if (!userService.existsEmail(email)) {
            return new Response(404);
        }
        if (!userService.auth(email, password)) {
            return new Response(401);
        }

        Object result = userService.findByEmail(email);
        return new Response(200, "OK", result);
    }
}
