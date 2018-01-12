package caas.code;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestCall {
    public static Object call(String url, Object payload) {

        HttpEntity<Object> requestEntity = new HttpEntity<>(payload, new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return resp.getBody();
    }

    public static void main(String[] args) {
        Map payload = new HashMap();
        payload.put("id", 1);
        System.out.println(call("http://localhost:8081/rpc/workspace/exists", payload));
    }
}
