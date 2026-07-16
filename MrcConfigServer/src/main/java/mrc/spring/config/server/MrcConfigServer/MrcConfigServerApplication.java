package mrc.spring.config.server.MrcConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MrcConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrcConfigServerApplication.class, args);
	}

}
