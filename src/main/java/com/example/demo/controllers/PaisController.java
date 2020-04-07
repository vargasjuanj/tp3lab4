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
  //Volver a hacer la petición para actualizar registros o guardar nuevos registros.
   // http://localhost:8102/api/v1/countries/consumeCountries
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

    /*
PUNTOS 5.1, 5.2, 5.3, 5.7 y 5.8
    GT : mas grande que
    NE : no igual a
    LT: menor que

    Ejemplos: Son filtros separados.

        Por defecto selecciona los paises con region Americas.
        Luego con region Americas y poblacon mayor a 100 millones
        Después Paises con region distinta a Africa .
        Y Por último países cuya población sea mayor a 50000000 y menor a 150000000. Los 50 millones están harcodeados en el método solo para respetar lo que se pide.

           http://localhost:8102/api/v1/countries/select

          http://localhost:8102/api/v1/countries/select?region=Europe&populationGT=20000000&regionNE=Asia

LOS
     */

@GetMapping("/select")
@Transactional
public ResponseEntity<?> getSelect(@RequestParam(value = "region", defaultValue = "Americas") String  region, @RequestParam(value = "populationGT", defaultValue = "100000000") int populationGT,
                                   @RequestParam(value = "regionNE", defaultValue = "Africa") String  regionNE, @RequestParam(value = "populationLT", defaultValue = "150000000") int populationLT) {

    try {

        return ResponseEntity.status(HttpStatus.OK).body(paisService.select(region,populationGT,regionNE, populationLT)+"\"\n Successful query!  Check your console}");

    } catch (Exception e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"All consultations were unsuccessful\": \"" + e.getMessage() + "\"}");
    }

}
// PUNTO 5.4

    //http://localhost:8102/api/v1/countries/update?name=Canada&newName=Actualizado&newPopulation=99
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> update(@RequestParam(value = "name", defaultValue = "Egypt") String  name,  @RequestParam(value = "newName", defaultValue = "Egipto") String  newName,
                                    @RequestParam(value = "newPopulation", defaultValue = "95000000") int  newPopulation) {

        try {

            return ResponseEntity.status(HttpStatus.OK).body(paisService.updateByCountryName(name,newName, newPopulation));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"The query was unsuccessful\": \"" + e.getMessage() + "\"}");
        }

    }
    //PUNTO 5.5
    // http://localhost:8102/api/v1/countries/delete?code=1
    @DeleteMapping("/delete")
    @Transactional
    public ResponseEntity<?> delete(@RequestParam(value = "code", defaultValue = "258") int code) {

        try {

            return ResponseEntity.status(HttpStatus.OK).body(paisService.deleteByCodeCountry(code)+"\"\n Successful query!  Check your console}");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"The query was unsuccessful\": \"" + e.getMessage() + "\"}");
        }

    }
}
