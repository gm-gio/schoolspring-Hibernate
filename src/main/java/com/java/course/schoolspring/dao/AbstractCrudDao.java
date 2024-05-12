package com.java.course.schoolspring.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class AbstractCrudDao<T, K> implements CrudDAO<T, K> {

    @PersistenceContext
    protected EntityManager em;

    public AbstractCrudDao(EntityManager entityManager) {
        this.em = entityManager;
    }
    @Override
    public T save(T entity) {
        if (em.contains(entity)) {
            return em.merge(entity);
        } else {
            em.persist(entity);
            return entity;
        }
    }
}



