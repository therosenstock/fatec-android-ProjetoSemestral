package com.example.vendasredessociais.persistence;

import java.sql.SQLException;

public interface IPedidoDao {
    public PedidoDao open() throws SQLException;
    public void close();
}
