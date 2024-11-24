package com.example.vendasredessociais.persistence;

import java.sql.SQLException;

public interface IProdutoDao {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    public ProdutoDao open() throws SQLException;
    public void close();
}
