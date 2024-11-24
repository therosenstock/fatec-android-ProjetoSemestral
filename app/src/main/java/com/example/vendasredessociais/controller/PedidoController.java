package com.example.vendasredessociais.controller;

import com.example.vendasredessociais.model.Pedido;
import com.example.vendasredessociais.persistence.PedidoDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PedidoController implements IController<Pedido> {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    private final PedidoDao pedidoDao;

    public PedidoController(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    @Override
    public void inserir(Pedido pedido) throws SQLException {
        if(pedidoDao.open() == null){
            pedidoDao.open();
        }
        pedidoDao.insert(pedido);
//        pedidoDao.close();
    }

    @Override
    public void modificar(Pedido pedido) throws SQLException {
        if(pedidoDao.open() == null){
            pedidoDao.open();
        }
        pedidoDao.update(pedido);
//        pedidoDao.close();
    }

    @Override
    public void deletar(Pedido pedido) throws SQLException {
        if(pedidoDao.open() == null){
            pedidoDao.open();
        }
        pedidoDao.delete(pedido);
//        pedidoDao.close();
    }

    @Override
    public Pedido buscar(Pedido pedido) throws SQLException {
        if(pedidoDao.open() == null){
            pedidoDao.open();
        }
        return pedidoDao.findOne(pedido);
    }

    @Override
    public List<Pedido> listar() throws SQLException {
        if(pedidoDao.open() == null){
            pedidoDao.open();
        }
        return pedidoDao.findAll();
    }

}
