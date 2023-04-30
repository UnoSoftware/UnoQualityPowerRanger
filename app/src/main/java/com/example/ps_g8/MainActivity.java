package com.example.ps_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_password, et_email;
    private ImageView showPassButton;
    Boolean checked = false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email = (EditText) findViewById(R.id.id_Email);
        et_password = (EditText) findViewById(R.id.id_Password);
        showPassButton = (ImageView) findViewById(R.id.showPassButton);
        showPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checked){
                    et_password.setInputType(129);
                    checked = false;
                } else{
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT);
                    checked = true;
                }
            }
        });
    }

    public void register(View view) {
        Intent start3 = new Intent(this, MainActivity5.class);
        startActivity(start3);
    }

    public void login(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            Cursor fila = BaseDatos.rawQuery("select * from usuario where email=? and contraseña =?", new String[]{email, password});
            if (fila.moveToFirst()) {
                BaseDatos.close();
                Intent start2 = new Intent(this, MainActivity2.class);
                start2.putExtra("usuario", email);
                Toast.makeText(getApplicationContext(), "BIENVENIDO "+email, Toast.LENGTH_SHORT).show();
                startActivity(start2);
            } else {
                Toast.makeText(this, "EROR: Datos introduccions no coinciden con cuenta existente", Toast.LENGTH_SHORT).show();
                BaseDatos.close();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "ERROR: Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}