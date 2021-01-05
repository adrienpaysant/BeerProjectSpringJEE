package ch.hearc.jee.beerproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PBeerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PBeerApplication.class, args);
	}

}
