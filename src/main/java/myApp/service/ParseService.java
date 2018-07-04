package myApp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import myApp.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class ParseService {
    @Autowired //to samo co @inject tylko inject jest ogolnie dla javy
    RestTemplate restTemplate;

    public Pokemon parsePokemon(String content){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(content, Pokemon.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
