package com.example.vendasredessociais.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDDao <T>{
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    public void insert(T t) throws SQLException;
    public int update(T t) throws SQLException;
    public void delete(T t) throws SQLException;
    public T findOne(T t) throws SQLException;
    public List<T> findAll() throws SQLException;
}

