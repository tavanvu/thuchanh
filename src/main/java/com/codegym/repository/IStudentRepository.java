package com.codegym.repository;

import com.codegym.General;
import com.codegym.model.Student;

import java.util.List;

public interface IStudentRepository extends General<Student> {
    List<Student> findByName(String name);
}
