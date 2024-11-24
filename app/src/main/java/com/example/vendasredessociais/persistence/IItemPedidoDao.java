package com.example.vendasredessociais.persistence;

import java.sql.SQLException;

public interface IItemPedidoDao {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    public ItemPedidoDao open() throws SQLException;
    public void close();
}
