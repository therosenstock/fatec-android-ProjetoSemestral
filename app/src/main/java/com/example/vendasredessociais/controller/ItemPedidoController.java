package com.example.vendasredessociais.controller;

import com.example.vendasredessociais.model.ItemPedido;
import com.example.vendasredessociais.persistence.ItemPedidoDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ItemPedidoController implements IController<ItemPedido>{
    private final ItemPedidoDao itemDao;

    public ItemPedidoController(ItemPedidoDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public void inserir(ItemPedido itemPedido) throws SQLException {
        if(itemDao.open() == null){
            itemDao.open();
        }
        itemDao.insert(itemPedido);
        itemDao.close();
    }

    @Override
    public void modificar(ItemPedido itemPedido) throws SQLException {
        if(itemDao.open() == null){
            itemDao.open();
        }
        itemDao.update(itemPedido);
        itemDao.close();
    }

    @Override
    public void deletar(ItemPedido itemPedido) throws SQLException {
        if(itemDao.open() == null){
            itemDao.open();
        }
        itemDao.delete(itemPedido);
        itemDao.close();
    }

    @Override
    public ItemPedido buscar(ItemPedido itemPedido) throws SQLException {
        if(itemDao.open() == null){
            itemDao.open();
        }
        return itemDao.findOne(itemPedido);
    }

    @Override
    public List<ItemPedido> listar() throws SQLException {
        if(itemDao.open() == null){
            itemDao.open();
        }
        return itemDao.findAll();
    }
}
