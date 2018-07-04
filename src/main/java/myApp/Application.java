package myApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	//TODO //	https://spring.io/guides/gs/rest-hateoas/

//	@Bean
//	public CommandLineRunner demo(CustomerRepository repository) {
//		return (args) -> {
//			// save a couple of customers
//			repository.save(new Pokemon("bulbasaur", "bulba"));
//			repository.save(new Pokemon("pikachu", "pika"));
//			repository.save(new Pokemon("chandaler", "chad"));
//			repository.save(new Pokemon("snorlake", "snor"));
//
//
//			// fetch all customers
//			log.info("Pokemony znalezione z findAll():");
//			log.info("-------------------------------");
//			for (Pokemon pokemon : repository.findAll()) {
//				log.info(pokemon.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			repository.findById(1L)
//				.ifPresent(pokemon -> {
//					log.info("Pokemon znaleziony z findById(1L):");
//					log.info("--------------------------------");
//					log.info(pokemon.toString());
//					log.info("");
//				});
//
//			// fetch customers by last name
//			log.info("Pokemon found with findByLastName('pikachu'):");
//			log.info("--------------------------------------------");
//			repository.findByName("pikachu").forEach(poka -> {
//				log.info(poka.toString());
//			});
//			// for (Pokemon bauer : repository.findByLastName("Bauer")) {
//			// 	log.info(bauer.toString());
//			// }
//			log.info("");
//		};
//	}

}
