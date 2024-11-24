package com.example.vendasredessociais.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vendasredessociais.model.Pedido;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao implements ICRUDDao<Pedido>, IPedidoDao {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public PedidoDao(Context context) {
        this.context = context;
    }
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */

    @Override
    public PedidoDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    public static ContentValues getContentValues(Pedido pedido){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cod_pedido", pedido.getCodigo());
        contentValues.put("nome_cliente", pedido.getNomeCliente());
        contentValues.put("status_pedido", pedido.getStatus());
        contentValues.put("preco_frete", pedido.getValorFrete());

        return contentValues;
    }
    @Override
    public void insert(Pedido pedido) throws SQLException {
        ContentValues contentValues = getContentValues(pedido);
        db.insert("pedido", null, contentValues);
    }

    @Override
    public int update(Pedido pedido) throws SQLException {
        ContentValues contentValues = getContentValues(pedido);
        int ret = db.update("pedido", contentValues, "cod_pedido = " + pedido.getCodigo(), null);
        return ret;
    }

    @Override
    public void delete(Pedido pedido) throws SQLException {
        db.delete("pedido", "cod_pedido = " + pedido.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Pedido findOne(Pedido pedido) throws SQLException {
        String sql = "SELECT * FROM pedido WHERE cod_pedido = "+ pedido.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            pedido.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_pedido")));
            pedido.setNomeCliente(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            pedido.setValorFrete(cursor.getDouble(cursor.getColumnIndex("preco_frete")));
            pedido.setStatus(cursor.getString(cursor.getColumnIndex("status_pedido")));
        }
        cursor.close();
        return pedido;
    }

    @SuppressLint("Range")
    @Override
    public List<Pedido> findAll() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Pedido pedido = new Pedido();
            pedido.setCodigo(cursor.getInt(cursor.getColumnIndex("cod_pedido")));
            pedido.setNomeCliente(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            pedido.setValorFrete(cursor.getDouble(cursor.getColumnIndex("preco_frete")));
            pedido.setStatus(cursor.getString(cursor.getColumnIndex("status_pedido")));
            pedidos.add(pedido);
            cursor.moveToNext();
        }
        cursor.close();
        return pedidos;
    }






}
