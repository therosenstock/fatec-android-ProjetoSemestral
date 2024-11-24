package com.example.vendasredessociais.controller;

import com.example.vendasredessociais.model.Produto;
import com.example.vendasredessociais.persistence.ProdutoDao;

import java.sql.SQLException;
import java.util.List;

public class ProdutoController implements IController<Produto> {
    private final ProdutoDao produtoDao;

    public ProdutoController(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    @Override
    public void inserir(Produto produto) throws SQLException {
        if(produtoDao.open() == null){
            produtoDao.open();
        }
        produtoDao.insert(produto);
//        produtoDao.close();
    }

    @Override
    public void modificar(Produto produto) throws SQLException {
        if(produtoDao.open() == null){
            produtoDao.open();
        }
        produtoDao.update(produto);
//        produtoDao.close();
    }

    @Override
    public void deletar(Produto produto) throws SQLException {
        if(produtoDao.open() == null){
            produtoDao.open();
        }
        produtoDao.delete(produto);
//        produtoDao.close();
    }

    @Override
    public Produto buscar(Produto produto) throws SQLException {
        if(produtoDao.open() == null){
            produtoDao.open();
        }
        return produtoDao.findOne(produto);
    }

    @Override
    public List<Produto> listar() throws SQLException {
        if(produtoDao.open() == null){
            produtoDao.open();
        }
        return produtoDao.findAll();

    }
    public List<Produto> listarComEstoque() throws SQLException {
        if(produtoDao.open() == null){
            produtoDao.open();
        }
        return produtoDao.findAllWithStorage();

    }

    public boolean possuiEstoque(int codProduto, int quantidade) throws SQLException{
        if(produtoDao.open() == null){
            produtoDao.open();
        }
        return produtoDao.possuiEstoque(codProduto, quantidade);
    }
}
