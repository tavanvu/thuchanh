package com.codegym;

import java.util.List;

public interface General<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void remove(Long id);
}
