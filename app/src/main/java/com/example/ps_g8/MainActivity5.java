package com.example.ps_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    private EditText et_password1, et_password2, et_email;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        et_email = (EditText) findViewById(R.id.id_Email3);
        et_password1 = (EditText) findViewById(R.id.id_Password1);
        et_password2 = (EditText) findViewById(R.id.id_Password2);
    }

    public void cancelRegister(View view){
        Intent cancel = new Intent(this, MainActivity.class);
        startActivity(cancel);
    }

    public void Register(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String email = et_email.getText().toString();
        String password1 = et_password1.getText().toString();
        String password2 = et_password2.getText().toString();

        if ((!email.isEmpty()) && (!password1.isEmpty()) && (!password2.isEmpty())) {
            if (!(password1.equals(password2))){
                Toast.makeText(getApplicationContext(), "ERROR: Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
            else {
                ContentValues registro = new ContentValues();
                if (isUsuario(email)==false) {
                    registro.put("email", email);
                    registro.put("contraseña", password1);
                    BaseDatos.insert("usuario", null, registro);
                    BaseDatos.close();

                    et_email.setText("");
                    et_password1.setText("");
                    et_password2.setText("");

                    AñadirRelacion(email);
                    Toast.makeText(getApplicationContext(), "Registro completado", Toast.LENGTH_SHORT).show();

                    Intent confirmar = new Intent(this, MainActivity.class);
                    startActivity(confirmar);
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR: Este email ya está registrado", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), "ERROR: Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isUsuario(String e) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        Cursor fila = BaseDatos.rawQuery("select * from usuario where email =?", new String[]{e});
        if (fila.moveToFirst()) {
            BaseDatos.close();
            return true;
        } else {
            BaseDatos.close();
            return false;
        }
    }

    public void AñadirRelacion(String email) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        Cursor c = BaseDatos.rawQuery("select * from pelicula", null);
        if (c.moveToFirst() && c.getCount() >= 1) {
            do {
                @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
                ContentValues cv = new ContentValues();
                cv.put("usuario", email);
                cv.put("id", id);
                cv.put("visto", 0);
                cv.put("gusta", 0);
                BaseDatos.insert("relacion", null, cv);
            } while (c.moveToNext());
        }
        c.close();
        BaseDatos.close();
    }
}