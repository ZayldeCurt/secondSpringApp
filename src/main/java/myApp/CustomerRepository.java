package myApp;

import java.util.List;

import myApp.dto.Pokemon;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Pokemon, Long> {

    List<Pokemon> findByName(String name);
}
