package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Pokemon, Long> {

    List<Pokemon> findByName(String name);
}
