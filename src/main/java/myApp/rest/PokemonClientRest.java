package myApp.rest;

import myApp.Pokemon;
import myApp.cache.PokemonCache;
import myApp.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//adnotacje Springa
//@Service //zostanie przy starcie utworzona pojedyńcza instancja, najczęściej używana, podstawowa //dodatkowo dodaje static do method
@RestController //mówi że tu będzie klasa świadcząca usługi restowe, czyli po http
public class PokemonClientRest {
    //ta klasa ma udostępnić endPoint
    //zczytamy z Pokemon i wyświetlimy jako swoje już

    private PokemonService pokemonService;
    private PokemonCache pokemonCache;


    @Autowired
    public PokemonClientRest(PokemonService pokemonService, PokemonCache pokemonCache) {
        this.pokemonService = pokemonService;
        this.pokemonCache = pokemonCache;
    }


//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @RequestMapping("/{endPoint}/{id}")//aby była widoczna jako usługa restowa, wystawia jako endpoint,
    // dzieki temu będzie ta funkcja widoczna z localhost:8080
    public Pokemon getOurPokemon(@PathVariable(value = "endPoint") String endPoint, @PathVariable(value = "id") String id) {

        if(endPoint.equals("pokemon")){
            Pokemon pokemon = pokemonCache.getPokemon(Long.parseLong(id));
            if(pokemon==null){
                pokemon = pokemonService.getPokemon(endPoint,id);
                pokemonCache.add(pokemon);
            }
            return pokemon;
        }
        return null;
    }

    @PostMapping("/addPokemon")
    public ResponseEntity<String> addPokemon(@RequestBody Pokemon pokemon){
        pokemonCache.add(pokemon);
        return null;
    }



    @RequestMapping("/show")
    public void showFromSQLBase(){
        Pokemon pokemon = pokemonCache.getPokemon(1L);
        System.out.println(pokemon);
    }

    @RequestMapping("/showAll")
    public List<Pokemon> showAllFromSQLBase(){
//        Pokemon pokemon = pokemonCache.getPokemon(1L);
        List<Pokemon> pokemonDtosList = pokemonCache.getAll();
        return pokemonDtosList;
    }

}
