package com.example.vendasredessociais.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vendasredessociais.model.ItemPedido;
import com.example.vendasredessociais.model.Pedido;
import com.example.vendasredessociais.model.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemPedidoDao implements ICRUDDao<ItemPedido>, IItemPedidoDao {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public ItemPedidoDao(Context context) {
        this.context = context;
    }

    @Override
    public ItemPedidoDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    public static ContentValues getContentValues(ItemPedido itemPedido){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cod_itempedido", itemPedido.getCodigoItem());
        contentValues.put("quantidade", itemPedido.getQuantidade());
        contentValues.put("cod_pedido", itemPedido.getPedido().getCodigo());
        contentValues.put("cod_produto", itemPedido.getProduto().getCodigo());
        return contentValues;
    }
    @Override
    public void insert(ItemPedido itemPedido) throws SQLException {
        ContentValues contentValues = getContentValues(itemPedido);
        db.insert("item_pedido", null, contentValues);
    }

    @Override
    public int update(ItemPedido itemPedido) throws SQLException {
        ContentValues contentValues = getContentValues(itemPedido);
        int ret = db.update("item_pedido", contentValues,"cod_itempedido = " + itemPedido.getCodigoItem(), null);
        return ret;
    }

    @Override
    public void delete(ItemPedido itemPedido) throws SQLException {
        db.delete("item_pedido","cod_itempedido = " + itemPedido.getCodigoItem(), null);
    }

    @SuppressLint("Range")
    @Override
    public ItemPedido findOne(ItemPedido itemPedido) throws SQLException {
        String sql = "SELECT i.cod_itempedido as cod_itempedido, " +
                "i.quantidade as quantidade, p.cod_produto as cod_produto, " +
                " p.nome_produto as nome_produto, p.preco_produto as preco_produto," +
                " p.quantidade as quantidade_estoque, pe.cod_pedido as cod_pedido, " +
                " pe.nome_cliente as nome_cliente, pe.preco_frete as preco_frete, pe.status_pedido as status_pedido " +
                " FROM item_pedido i " +
                "INNER JOIN produto p  ON p.cod_produto = i.cod_produto " +
                "INNER JOIN pedido pe ON pe.cod_pedido = i.cod_pedido" +
                " WHERE i.cod_itempedido = " + itemPedido.getCodigoItem() + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            Produto produto = new Produto();
            produto.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
            produto.setPreco(cursor.getDouble(cursor.getColumnIndex("preco_produto")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade_estoque")));

            Pedido pedido = new Pedido();
            pedido.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_pedido")));
            pedido.setNomeCliente(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            pedido.setValorFrete(cursor.getDouble(cursor.getColumnIndex("preco_frete")));
            pedido.setStatus(cursor.getString(cursor.getColumnIndex("status_pedido")));

            itemPedido.setCodigoItem(cursor.getInt(cursor.getColumnIndex("cod_itempedido")));
            itemPedido.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
        }
        cursor.close();
        return itemPedido;
    }

    @SuppressLint("Range")
    @Override
    public List<ItemPedido> findAll() throws SQLException {
        List<ItemPedido> itemsPedido = new ArrayList<>();
        String sql = "SELECT i.cod_itempedido as cod_itempedido, " +
                "i.quantidade as quantidade, p.cod_produto as cod_produto, " +
                " p.nome_produto as nome_produto, p.preco_produto as preco_produto," +
                " p.quantidade as quantidade_estoque, pe.cod_pedido as cod_pedido, " +
                " pe.nome_cliente as nome_cliente, pe.preco_frete as preco_frete, pe.status_pedido as status_pedido " +
                " FROM item_pedido i " +
                "INNER JOIN produto p  ON p.cod_produto = i.cod_produto " +
                "INNER JOIN pedido pe ON pe.cod_pedido = i.cod_pedido;";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Produto produto = new Produto();
            produto.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
            produto.setPreco(cursor.getDouble(cursor.getColumnIndex("preco_produto")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade_estoque")));

            Pedido pedido = new Pedido();
            pedido.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_pedido")));
            pedido.setNomeCliente(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            pedido.setValorFrete(cursor.getDouble(cursor.getColumnIndex("preco_frete")));
            pedido.setStatus(cursor.getString(cursor.getColumnIndex("status_pedido")));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setCodigoItem(cursor.getInt(cursor.getColumnIndex("cod_itempedido")));
            itemPedido.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            itemsPedido.add(itemPedido);
            cursor.moveToNext();
        }
        cursor.close();
        return itemsPedido;
    }
    @SuppressLint("Range")
    public List<ItemPedido> findItemsPedido(int busca) throws SQLException {

        List<ItemPedido> itemsPedido = new ArrayList<>();
        String sql = "SELECT i.cod_itempedido as cod_itempedido, " +
                "i.quantidade as quantidade, p.cod_produto as cod_produto, " +
                " p.nome_produto as nome_produto, p.preco_produto as preco_produto," +
                " p.quantidade as quantidade_estoque, pe.cod_pedido as cod_pedido, " +
                " pe.nome_cliente as nome_cliente, pe.preco_frete as preco_frete, pe.status_pedido as status_pedido " +
                " FROM item_pedido i " +
                "INNER JOIN produto p  ON p.cod_produto = i.cod_produto " +
                "INNER JOIN pedido pe ON pe.cod_pedido = i.cod_pedido" +
                " WHERE i.cod_pedido = "+busca+" ;";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Produto produto = new Produto();
            produto.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
            produto.setPreco(cursor.getDouble(cursor.getColumnIndex("preco_produto")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade_estoque")));

            Pedido pedido = new Pedido();
            pedido.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_pedido")));
            pedido.setNomeCliente(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            pedido.setValorFrete(cursor.getDouble(cursor.getColumnIndex("preco_frete")));
            pedido.setStatus(cursor.getString(cursor.getColumnIndex("status_pedido")));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setCodigoItem(cursor.getInt(cursor.getColumnIndex("cod_itempedido")));
            itemPedido.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            itemsPedido.add(itemPedido);
            cursor.moveToNext();
        }
        cursor.close();
        return itemsPedido;
    }

}
