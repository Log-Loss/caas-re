package caas.train.Training;

import caas.train.util.RestCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @RequestMapping(value = "/train", method = RequestMethod.POST)
    public Object trainModel(@RequestBody Map<String, String> body) {
        try {
            String conf = body.get("conf");
            String type = body.get("type");
            String datasetName = body.get("datasetName");
            Integer epochs = Integer.valueOf(body.get("epochs"));
            Integer batchSize = Integer.valueOf(body.get("batchSize"));
            Integer jobId = Integer.valueOf(body.get("jobId"));

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            HttpEntity<String> requestEntity = new HttpEntity<>(conf, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> resp = restTemplate.exchange("http://localhost:10001/" + type, HttpMethod.POST, requestEntity, String.class);
            List<String> val = resp.getHeaders().get("Set-Cookie");
            String realConf = resp.getBody();
            return trainingService.trainModel(jobId, realConf, datasetName, epochs, batchSize);
        } catch (Exception e) {
            RestCall.jobUpdate(Integer.valueOf(body.get("jobId")), null, null, null, e.toString());
            return null;
        }

    }
}
