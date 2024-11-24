package com.example.vendasredessociais.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class GenericDao extends SQLiteOpenHelper {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */
    private static final String DATABASE = "VENDAS.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_PRODUTO =
            "CREATE TABLE produto( " +
                    "cod_produto INT PRIMARY KEY NOT NULL, " +
                    "nome_produto VARCHAR(100), " +
                    "preco_produto REAL, " +
                    "quantidade INT);";
    private static final String CREATE_TABLE_PEDIDO =
            "CREATE TABLE pedido( " +
                    "  cod_pedido INT PRIMARY KEY NOT NULL, " +
                    "  nome_cliente VARCHAR(120), " +
                    "  preco_frete REAL, " +
                    "  status_pedido VARCHAR(50) " +
                    ");";
    private static final String CREATE_TABLE_ITEM_PEDIDO =
            "CREATE TABLE item_pedido( " +
                    "  cod_itempedido INT PRIMARY KEY NOT NULL, " +
                    "  cod_pedido INT NOT NULL, " +
                    "  cod_produto INT NOT NULL, " +
                    "  quantidade INT, " +
                    "  FOREIGN KEY (cod_pedido) REFERENCES pedido(cod_pedido), " +
                    "  FOREIGN KEY (cod_produto) REFERENCES produto(cod_produto) " +
                    ");";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUTO);
        db.execSQL(CREATE_TABLE_PEDIDO);
        db.execSQL(CREATE_TABLE_ITEM_PEDIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS produto");
            db.execSQL("DROP TABLE IF EXISTS pedido");
            db.execSQL("DROP TABLE IF EXISTS item_pedido");
            onCreate(db);
        }
    }
}
