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

    public void agregarPelis(SQLiteDatabase BaseDatos) {
        ContentValues reg = new ContentValues();
        for (int i = 1; i <= 7; i++) {
            switch (i) {
                case 1:
                    reg.put("id", String.valueOf(i));
                    reg.put("nombre", "SPIDERMAN");
                    reg.put("año", "2002");
                    reg.put("sinopsis", "sinopsis de la peli 1");
                    BaseDatos.insert("pelicula", null, reg);
                    break;

                case 2:
                    reg.put("id", String.valueOf(i));
                    reg.put("nombre", "TITANIC");
                    reg.put("año", "1997");
                    reg.put("sinopsis", "sinopsis de la peli 2");
                    BaseDatos.insert("pelicula", null, reg);
                    break;

                case 3:
                    reg.put("id", String.valueOf(i));
                    reg.put("nombre", "STAR WARS");
                    reg.put("año", "1977");
                    reg.put("sinopsis", "sinopsis de la peli 3");
                    BaseDatos.insert("pelicula", null, reg);
                    break;

                case 4:
                    reg.put("id", String.valueOf(i));
                    reg.put("nombre", "EL HOMBRE DE ACERO");
                    reg.put("año", "2013");
                    reg.put("sinopsis", "sinopsis de la peli 4");
                    BaseDatos.insert("pelicula", null, reg);
                    break;

                case 5:
                    reg.put("id", String.valueOf(i));
                    reg.put("nombre", "JUMANJI");
                    reg.put("año", "1995");
                    reg.put("sinopsis", "sinopsis de la peli 5");
                    BaseDatos.insert("pelicula", null, reg);
                    break;

                case 6:
                    reg.put("id", String.valueOf(i));
                    reg.put("nombre", "SIN PERDÓN");
                    reg.put("año", "1992");
                    reg.put("sinopsis", "sinopsis de la peli 6");
                    BaseDatos.insert("pelicula", null, reg);
                    break;

                case 7:
                    reg.put("id", String.valueOf(i));
                    reg.put("nombre", "MATRIX");
                    reg.put("año", "1999");
                    reg.put("sinopsis", "sinopsis de la peli 7");
                    BaseDatos.insert("pelicula", null, reg);
                    break;
            }
        }
    }
}