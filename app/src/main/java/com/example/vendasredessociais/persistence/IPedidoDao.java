package com.example.vendasredessociais.persistence;

import java.sql.SQLException;

public interface IPedidoDao {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    public PedidoDao open() throws SQLException;
    public void close();
}
