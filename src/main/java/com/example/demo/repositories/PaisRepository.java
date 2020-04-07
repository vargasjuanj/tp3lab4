package com.example.demo.repositories;



import com.example.demo.model.Pais;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends MongoRepository<Pais, String> {
    /*
    En algunos casos la query no es necesaria, se resuelve por query methods, el ?0 corresponde al primer parametro y asi sucesivamente. Devuelve el objeto según el repositorio en que esté.
    Para más personalizado armar un dto que mientras tenga los sets de ciertos atributos funciona.
     */

    //@Query("{codigoPais:?0}")
    Pais findByCodigoPais(int codigoPais);

    //@Query("{region:?0}")
   List<Pais> findByRegion(String region);

    //@Query("{$and:[{'region':?0},{'poblacion':{$gt:?1}}]}")
    List<Pais> findByRegionAndPoblacionGreaterThan( String region,int poblacionGT);

    //@Query("{region:{$ne:?0}}")
    List<Pais> findByRegionNot(String regionNE);

    //@Query("{nombrePais:?0}")
    Pais findByNombrePais(String nombrePais);

    @Query("{$and:[{poblacion:{$gt:?0}},{poblacion:{$lt:?1}}]}")
    List<Pais> findByPoblacionGreaterThanAndPoblacionLessThan(int populationGT, int populationLT);

    List<Pais> OrderByNombrePaisAsc();

}
