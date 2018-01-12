package caas.dataset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DatasetApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasetApplication.class, args);
	}
}
