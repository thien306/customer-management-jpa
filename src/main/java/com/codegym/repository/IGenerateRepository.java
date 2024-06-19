package com.codegym.repository;

import java.util.List;

public interface IGenerateRepository<T> {

    List<T> findAll();

    T findById(Long id);

    void save(T T);

    void delete(Long id);
}
