package com.example.ps_g8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public final class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDatos) {
        BaseDatos.execSQL("create table usuario" +
                "(email string primary key," +
                "contraseña string not null)");

        BaseDatos.execSQL("create table pelicula(id string primary key,nombre string not null, año string not null, sinopsis string not null)");
        BaseDatos.execSQL("create table relacion" +
                "(usuario string," +
                "id string," +
                "visto int," +
                "gusta int," +
                "foreign key(usuario) references usuario(cod_usuario)," +
                "foreign key(id) references pelicula(id))");
        this.agregarPelis(BaseDatos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    private void BD(String i, String nombre, String año, String sinopsis, SQLiteDatabase BaseDatos){
        ContentValues reg = new ContentValues();

        reg.put("id", i);
        reg.put("nombre", nombre);
        reg.put("año", año);
        reg.put("sinopsis", sinopsis);

        BaseDatos.insert("pelicula", null, reg);
    }


    public void agregarPelis(SQLiteDatabase BaseDatos) {
        BD("1","SPIDERMAN","2002",  "sinopsis de la peli 1", BaseDatos);
        BD("2","TITANIC","1997","sinopsis de la peli 2", BaseDatos);
        BD("3","STAR WARS", "1977","sinopsis de la peli 3", BaseDatos);
        BD("4","EL HOMBRE DE ACERO", "2013","sinopsis de la peli 4", BaseDatos);
        BD("5","JUMANJI","1995","sinopsis de la peli 5", BaseDatos);
        BD("6","SIN PERDÓN", "1992","sinopsis de la peli 6", BaseDatos);
        BD("7", "MATRIX", "1999","sinopsis de la peli 7", BaseDatos);
    }
}