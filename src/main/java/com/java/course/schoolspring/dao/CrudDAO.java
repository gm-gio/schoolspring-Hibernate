package com.java.course.schoolspring.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T, K> {
    Optional<T> findById(K id);

    List<T> findAll();

    T save(T entity);

    void deleteById(K id);

    void saveAll(List<T> entities);

    int count();
}
