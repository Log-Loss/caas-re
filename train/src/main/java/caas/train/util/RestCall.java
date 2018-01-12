package caas.train.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RestCall {

    private static String workspaceUrl = "http://localhost:8081/rpc/workspace/";
    private static String jobUrl = "http://localhost:8081/rpc/job/";

    private static String call(String url, Object payload) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(payload, new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return resp.getBody();
    }

    public static Boolean workspaceExists(Integer workspaceId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", workspaceId);
        return Boolean.valueOf(call(workspaceUrl + "exists", payload));
    }

    public static void jobUpdate(Integer jobId, String state, Double accuracy, Date finishTime, String message) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", jobId);
        payload.put("state", state);
        payload.put("accuracy", accuracy);
        payload.put("finishTime", finishTime);
        payload.put("message", message);
        call(jobUrl + "update", payload);
    }

    public static void main(String[] args) {
        java.util.Date time = new java.util.Date(new Date().getTime());
        System.out.println(time);
    }
}
