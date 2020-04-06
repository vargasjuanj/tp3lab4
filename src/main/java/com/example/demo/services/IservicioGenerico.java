package com.example.demo.services;

import java.util.List;

public interface IservicioGenerico<E> {

    E findById(String id) throws Exception;

    E save(E entityForm) throws Exception;

    E update(String id, E entityForm) throws Exception;

    int countPages(int size) throws Exception;

    List<E> findAll(int page, int size) throws Exception;

    List<E> findAll() throws Exception;

    boolean delete(String id) throws Exception;


}