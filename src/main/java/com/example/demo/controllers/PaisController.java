package com.example.demo.controllers;

import com.example.demo.model.Pais;
import com.example.demo.services.PaisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController

@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping(path = "api/v1/countries")
public class PaisController extends ControllerGenerico<Pais, PaisService> {
    private static final Logger log = LoggerFactory.getLogger(PaisController.class);
    @Autowired
    private PaisService paisService;

//Nota: Sacandole la anotación @Bean al método consumeApi del servicio Pais puedo enviar la request con postman o el navegador.
//Esperar a que inserte todos para ver los registros.
    @PostMapping("/consumeCountries")
     @Transactional
    public ResponseEntity<?> getAll() {

        try {

            return ResponseEntity.status(HttpStatus.OK).body(paisService.consumeApi());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Countries not found or not saved or updated\": \"" + e.getMessage() + "\"}");
        }

    }


}
