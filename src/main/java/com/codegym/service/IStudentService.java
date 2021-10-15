package com.codegym.service;

import com.codegym.General;
import com.codegym.model.Student;

import java.util.List;

public interface IStudentService extends General<Student> {
    List<Student> findByName(String name);
}
