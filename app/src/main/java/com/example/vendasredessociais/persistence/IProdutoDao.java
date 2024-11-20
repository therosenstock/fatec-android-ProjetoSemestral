package com.example.vendasredessociais.persistence;

import java.sql.SQLException;

public interface IProdutoDao {
    public ProdutoDao open() throws SQLException;
    public void close();
}
