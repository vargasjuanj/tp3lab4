package com.example.demo.services;

import com.example.demo.model.Pais;
import com.example.demo.repositories.PaisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class PaisService extends ServicioGenerico<Pais, PaisRepository> {
    /*
    @Bean : le dice a Spring 'aquí hay una instancia de esta clase, por favor manténgala y devuélvamela cuando pregunte'.
@Autowired : dice 'por favor, dame una instancia de esta clase, por ejemplo, una que haya creado con una @Beananotación anterior
     */
    List<String>ListUpdatedRecords= new ArrayList<>();
    List<String>ListSavedRecords= new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(PaisService.class);
    private long countSaved, countUpdated;
    @Autowired
    private PaisRepository paisRepository;
    private String uri;

    @Bean //Si pusiera este Bean en consumeApi, se ejecutaria automaticamente sin necesitar una petición de postman o el navegador. .
    public RestTemplate createRestTemplate() {
        return new RestTemplateBuilder().build();
    }

    public List<Pais> consumeApi() throws Exception {
        RestTemplate restTemplate = createRestTemplate(); //RestTemplate es sincrono, WebClient lo reemplazará y es asincrono o reactivo.
        uri = "https://restcountries.eu/rest/v2/callingcode/";
        Pais[] pais;

        for (int code = 1; code <= 300; code++) {

            try {
                pais = restTemplate.getForObject(
                        uri + code, Pais[].class);
                updatedOrSaved(pais[0]);
            } catch (Exception e) {
                log.info("Registration with code  " + code + " does not exist in " + uri + code);

            }
        }
        log.info("Updated records " +ListUpdatedRecords.toString());
        log.info("Saved records" +ListSavedRecords.toString());
        log.info(getCountUpdated()+" records updated in total");
        log.info(getCountSaved()+" records saved in total");
        uri = null;
    countSaved=0;
    countUpdated=0;

    return findAll();
    }

    @Transactional
    public void updatedOrSaved(Pais dto) throws Exception {


        try {

            Pais entity = paisRepository.findByCodigoPais(dto.getCallingCodes()[0]);
            dto=prepare(dto);  //seteo codigoPais y coordenadas;
            if (entity.equals(dto)) {

                log.info("No changes found, no new records");
                return;
            } else {
                dto.setId(entity.getId());
                save(dto); //En este caso el método save actualiza
                ListUpdatedRecords.add(dto.toString());
                countUpdated++;

                log.info("Updated successfully, code "+dto.getCodigoPais());
            }
        }catch(Exception e) {
            dto=prepare(dto);
            save(dto);   //Si el id no existe en la base de datos, guarda el registro
            ListSavedRecords.add(dto.toString());
            countSaved++;
            log.info("Saved successfully, code "+dto.getCodigoPais());
            return;

        }

    }

    public Pais prepare(Pais dto){
        dto.setCodigoPais(dto.getCallingCodes()[0]); //Establezco código capturado como array de un json  (no persistente, transient ) en un atributo si persistente
        dto.setLatitud(dto.getLatlng()[0]); //Establezco latitud del array latlng capturado del json
        dto.setLongitud(dto.getLatlng()[1]); //Establezco longitud
        return dto;
    }

    public long getCountSaved() {
        return countSaved;
    }

    public long getCountUpdated() {
        return countUpdated;
    }

  public  boolean select(String region, int populationGT, String regionNE, int populationLT) throws Exception {
        try{
            log.info(" \n ---------- By "+region+" region ---------- \n");
            showQueries(repository.findByRegion(region));

            log.info(" \n ---------- By " +region+" region and population greater than "+populationGT+" ---------- \n");
            showQueries(repository.findByRegionAndPoblacionGreaterThan(region,populationGT));

            log.info(" \n ---------- By region other than "+regionNE+" ---------- \n");
            showQueries(repository.findByRegionNot(regionNE));


            log.info(" \n ---------- By population greater than 50000000 and less than "+populationLT+" ---------- \n");
            showQueries(repository.findByPoblacionGreaterThanAndPoblacionLessThan(50000000,populationLT));

            log.info(" \n ---------- Sorted by country name in ascending order  ---------- \n");
            showQueries(repository.OrderByNombrePaisAsc());


            return true;
        }catch (Exception e){
            log.info(e.getStackTrace().toString());
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

  }

    public Pais updateByCountryName(String name, String newName, int newPopulation) throws Exception {
        try{
            log.info(" \n ---------- POINT 5.4 ---------- \n");
            Pais pais=repository.findByNombrePais(name);
            log.info("Before : "+pais.toString());
            pais.setNombrePais(newName);
            pais.setPoblacion(newPopulation);
            save(pais);
            log.info("After : "+pais.toString());
            return pais;
        }catch (Exception e){
            log.info(e.getStackTrace().toString());
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    public boolean  deleteByCodeCountry(int code){
        try{
            log.info(" \n ---------- POINT 5.5 ---------- \n");
            Pais pais=repository.findByCodigoPais(code);
            log.info("Country with code "+code+" : "+pais.toString());
            delete(pais.getId());
            log.info("The country with code "+code+" was removed");
            return true;
        }catch (Exception e){
            log.info(e.getStackTrace().toString());
            log.info(e.getMessage());
            return false;
        }
    }
   public void showQueries(List<Pais> countriesList){
 countriesList.forEach(country-> log.info(country.toString()));
    }

}