package cotato.hacakton.petg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetgApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetgApplication.class, args);
	}

}
