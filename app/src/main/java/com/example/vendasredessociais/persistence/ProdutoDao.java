package com.example.vendasredessociais.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vendasredessociais.model.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoDao implements IProdutoDao, ICRUDDao<Produto>{
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public ProdutoDao(Context context) {
        this.context = context;
    }

    @Override
    public ProdutoDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Produto produto) throws SQLException {
        ContentValues contentValues = getContentValues(produto);
        db.insert("produto", null, contentValues);
    }

    public static ContentValues getContentValues(Produto produto){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cod_produto", produto.getCodigo());
        contentValues.put("nome_produto", produto.getNome());
        contentValues.put("preco_produto", produto.getPreco());
        contentValues.put("quantidade", produto.getQuantidade());
        return contentValues;
    }

    @Override
    public int update(Produto produto) throws SQLException {
        ContentValues contentValues = getContentValues(produto);
        int ret = db.update("produto", contentValues, "cod_produto = " + produto.getCodigo(), null);
        return ret;
    }
    @Override
    public void delete(Produto produto) throws SQLException {
        db.delete("produto", "cod_produto = " + produto.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Produto findOne(Produto produto) throws SQLException {
        String sql = "SELECT * FROM produto WHERE cod_produto = "+ produto.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            produto.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
            produto.setPreco(cursor.getDouble(cursor.getColumnIndex("preco_produto")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
        }
        cursor.close();
        return produto;
    }

    @SuppressLint("Range")
    @Override
    public List<Produto> findAll() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY cod_produto";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Produto produto = new Produto();
            produto.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
            produto.setPreco(cursor.getDouble(cursor.getColumnIndex("preco_produto")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            produtos.add(produto);
            cursor.moveToNext();
        }
        cursor.close();
        return produtos;
    }

    @SuppressLint("Range")
    public List<Produto> findAllWithStorage() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE quantidade > 0";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Produto produto = new Produto();
            produto.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
            produto.setPreco(cursor.getDouble(cursor.getColumnIndex("preco_produto")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            produtos.add(produto);
            cursor.moveToNext();
        }
        cursor.close();
        return produtos;
    }

    public boolean possuiEstoque(int codProduto, int quantidadeDesejada) {
        String sql = "SELECT quantidade FROM produto WHERE cod_produto = " + codProduto;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int quantidadeAtual = cursor.getInt(0);

            if (quantidadeAtual >= quantidadeDesejada) {
                cursor.close();
                return true;
            } else {
                cursor.close();
                throw new IllegalArgumentException("Quantidade insuficiente no estoque para o produto " + codProduto);
            }
        } else {
            cursor.close();
            throw new IllegalArgumentException("Produto não encontrado: " + codProduto);
        }
    }



}
