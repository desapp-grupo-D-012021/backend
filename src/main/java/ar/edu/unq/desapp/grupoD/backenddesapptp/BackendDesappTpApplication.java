package ar.edu.unq.desapp.grupoD.backenddesapptp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
public class BackendDesappTpApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDesappTpApplication.class, args);
	}

}
