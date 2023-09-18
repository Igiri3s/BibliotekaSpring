package labs.savangard.spring.zad1.biblioteka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BibliotekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotekaApplication.class, args);
	}

}
