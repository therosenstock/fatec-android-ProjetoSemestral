package com.example.vendasredessociais.controller;

import java.sql.SQLException;
import java.util.List;

public interface IController<T> {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    public void inserir(T t) throws SQLException;
    public void modificar(T t) throws SQLException;
    public void deletar(T t) throws SQLException;
    public T buscar (T t) throws SQLException;
    public List<T> listar() throws SQLException;
}
