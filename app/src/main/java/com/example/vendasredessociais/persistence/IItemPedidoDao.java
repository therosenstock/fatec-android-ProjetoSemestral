package com.example.vendasredessociais.persistence;

import java.sql.SQLException;

public interface IItemPedidoDao {
    public ItemPedidoDao open() throws SQLException;
    public void close();
}
