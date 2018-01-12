package caas.poc.rpc;

import caas.poc.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class JobRPC {
    @Autowired
    JobService jobService;

    @RequestMapping(value = "/rpc/job/update", method = RequestMethod.POST)
    public Object exists(@RequestBody Map<String, String> body) {
        Double accuracy;
        Date finishTime;
        String state;
        String message;
        if (body.get("state") == null) {
            state = null;
        } else {
            state = body.get("state");
        }
        if (body.get("accuracy") == null) {
            accuracy = null;
        } else {
            accuracy = Double.valueOf(body.get("accuracy"));
        }
        if (body.get("finishTime") == null) {
            finishTime = null;
        } else {
            finishTime = new Date(Long.valueOf(body.get("finishTime")));
        }
        if (body.get("message") == null) {
            message = null;
        } else {
            message = body.get("message");
        }
        return jobService.update(Integer.valueOf(body.get("id")), state, accuracy, finishTime, message);
    }
}