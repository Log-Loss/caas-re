package caas.incrementid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IncrementIdApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncrementIdApplication.class, args);
	}
}
