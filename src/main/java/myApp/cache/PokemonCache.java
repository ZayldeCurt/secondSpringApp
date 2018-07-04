package myApp.cache;

import myApp.Pokemon;
import myApp.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PokemonCache {

    public List<Pokemon> pokemonDtosList;
    @Autowired
    public PokemonRepository pokemonRepository;
    @PostConstruct
    public void methodInit() {
        pokemonDtosList = new ArrayList<>();
    }

    public void add(Pokemon pokemonDto){
        pokemonRepository.save(pokemonDto);
    }

    public Pokemon getPokemon(Long id){
        Optional<Pokemon> pokemonById = pokemonRepository.findById(id);
        if(pokemonById.isPresent()){
            return pokemonById.get();
        }
        return null;
    }

    public List<Pokemon> getAll(){
        Iterable<Pokemon> all = pokemonRepository.findAll();

//        pokemonDtosList = all;
        pokemonDtosList = StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
        return pokemonDtosList;
    }
}