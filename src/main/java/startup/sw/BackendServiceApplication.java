package startup.sw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"startup.sw.controllers", "startup.sw.services", "startup.sw.services.impl", "startup.sw.entities", "startup.sw.security"})
@EnableJpaRepositories("startup.sw.repositories")
@RestController
public class BackendServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BackendServiceApplication.class, args);
	}

}
