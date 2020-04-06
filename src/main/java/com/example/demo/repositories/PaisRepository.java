package com.example.demo.repositories;



import com.example.demo.model.Pais;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends MongoRepository<Pais, String> {
    Pais findByCodigoPais(int codigoPais);
}
