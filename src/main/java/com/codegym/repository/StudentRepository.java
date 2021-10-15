package com.codegym.repository;

import com.codegym.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
@Transactional
public class StudentRepository implements IStudentRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query=entityManager.createQuery("select s from Student as s",Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        TypedQuery<Student> query=entityManager.createQuery("select s from Student as s where s.id=:id" ,Student.class);
       query.setParameter("id",id);
        return  query.getSingleResult();
    }

    @Override
    public void save(Student student) {
        if (student.getId() !=null){
            entityManager.merge(student);
        }else {
            entityManager.persist(student);
        }
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return null;
    }
}
