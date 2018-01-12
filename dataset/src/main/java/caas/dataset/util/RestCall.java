package caas.dataset.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestCall {

    private static String workspaceUrl = "http://localhost:8081/rpc/workspace/";

    private static String idUrl = "http://localhost:10005/rpc/increamentid";

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

    public static Integer idGenerate(String name) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        return Integer.valueOf(call(idUrl, payload));
    }

    public static void main(String[] args){
        System.out.println(idGenerate("a"));
        System.out.println(workspaceExists(1));
    }
}
