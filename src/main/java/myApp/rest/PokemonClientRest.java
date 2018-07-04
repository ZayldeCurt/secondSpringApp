package myApp.rest;

import myApp.Pokemon;
import myApp.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

//adnotacje Springa
//@Service //zostanie przy starcie utworzona pojedyńcza instancja, najczęściej używana, podstawowa //dodatkowo dodaje static do method
@RestController //mówi że tu będzie klasa świadcząca usługi restowe, czyli po http
public class PokemonClientRest {
    //ta klasa ma udostępnić endPoint
    //zczytamy z Pokemon i wyświetlimy jako swoje już

    private PokemonService pokemonService;


    @Autowired
    public PokemonClientRest(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/{endPoint}/{id}")//aby była widoczna jako usługa restowa, wystawia jako endpoint,
    // dzieki temu będzie ta funkcja widoczna z localhost:8080
    public Pokemon getOurPokemon(@PathVariable(value = "endPoint") String endPoint, @PathVariable(value = "id") String id) {

        Pokemon pokemon = pokemonService.getPokemon(endPoint,id);

        return pokemon;
    }

//    @PostMapping("/addPokemon")
//    public ResponseEntity<String> addPokemon(@RequestBody PokemonDto pokemonDto){
//        pokemonCache.add(pokemonDto);
//        return null;
//    }
//
//    @RequestMapping("/showAll")
//    public void showAllFromSQLBase(){
//        pokemonCache.showAll();
//    }

}
