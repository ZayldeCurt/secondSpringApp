package myApp.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import myApp.dto.Greeting;
import myApp.dto.Pokemon;
import myApp.cache.PokemonCache;
import myApp.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



//    @RequestMapping("/show")
//    public void showFromSQLBase(){
//        Pokemon pokemon = pokemonCache.getPokemon(1L);
//        System.out.println(pokemon);
//    }


    @RequestMapping("/show")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "id", required = false, defaultValue = "World") String id) {

        Pokemon pokemon = pokemonCache.getPokemon(Long.parseLong(id));
        Long longId = Long.parseLong(id);
        Long nextId = Long.parseLong(id)+1;
        Long preId = Long.parseLong(id)-1;


//        Greeting greeting = new Greeting(String.format(" ", id));
        Greeting greeting = new Greeting(String.format(pokemon.toString(), id));
        greeting.add(linkTo(methodOn(PokemonClientRest.class).greeting(id)).withSelfRel());
        greeting.add(linkTo(methodOn(PokemonClientRest.class).greeting(String.valueOf(nextId))).withRel("next"));
        greeting.add(linkTo(methodOn(PokemonClientRest.class).greeting(String.valueOf(preId))).withRel("pre"));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

//    @RequestMapping("/greeting")
//    public HttpEntity<Greeting> greeting(
//            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
//
//        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
//        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
//
//        return new ResponseEntity<>(greeting, HttpStatus.OK);
//    }


    @RequestMapping("/showAll")
    public List<Pokemon> showAllFromSQLBase(){
//        Pokemon pokemon = pokemonCache.getPokemon(1L);
        List<Pokemon> pokemonDtosList = pokemonCache.getAll();
        return pokemonDtosList;
    }

}
