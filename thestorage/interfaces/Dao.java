package com.thestorage.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
    
    Exception getLastException();
}
