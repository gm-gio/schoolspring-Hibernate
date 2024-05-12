package com.java.course.schoolspring.dao.impl;

import com.java.course.schoolspring.dao.AbstractCrudDao;
import com.java.course.schoolspring.dao.GroupDAO;
import com.java.course.schoolspring.model.Group;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupDAOImpl extends AbstractCrudDao<Group, Integer> implements GroupDAO {

    public GroupDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public int count() {
        return em.createQuery("SELECT COUNT(g) FROM Group g", Long.class)
                .getSingleResult().intValue();
    }

    @Override
    public List<Group> findGroupsByStudentCount(int maxStudentCount) {
        return em.createQuery(
                        "SELECT g " +
                                "FROM Group g " +
                                "WHERE SIZE(g.students) <= :maxStudentCount", Group.class)
                .setParameter("maxStudentCount", maxStudentCount)
                .getResultList();
    }


    @Override
    public Group create(Group group) {
        em.persist(group);
        return group;
    }

    @Override
    public Optional<Group> findById(Integer id) {
        return Optional.ofNullable(em.find(Group.class, id));
    }


    @Override
    public List<Group> findAll() {
        return em.createQuery("SELECT g FROM Group g", Group.class)
                .getResultList();
    }


    @Override
    public Group update(Group group) {
        return em.merge(group);
    }

    @Override
    public void deleteById(Integer id) {
        Group group = em.find(Group.class, id);
        if (group != null) {
            em.remove(group);
        }
    }

    @Override
    public void saveAll(List<Group> groups) {
        groups.forEach(this::create);
    }

}