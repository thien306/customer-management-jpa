package com.codegym.service;

import java.util.List;

public interface IGenerateRepository<T> {

    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void delete(Long id);
}
