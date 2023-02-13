package com.example.ps_g8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity6 extends AppCompatActivity {
    private Button btn1;
    ImageView img;
    TextView tit,sin,year;
    View view;

    @Override  /*m*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        img = findViewById(R.id.imagen);
        tit = findViewById(R.id.titulo);
        sin = findViewById(R.id.sinopsis);
        year = findViewById(R.id.año);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        int idPelicula = getIntent().getExtras().getInt("pelicula");
        Cursor c = BaseDatos.rawQuery("select * from pelicula where id =" + idPelicula, null);
        if (c.moveToFirst() && c.getCount() >= 1) {
            @SuppressLint("Range") String titulo = c.getString(c.getColumnIndex("nombre"));
            tit.setText(titulo);
            @SuppressLint("Range") String año = c.getString(c.getColumnIndex("año"));
            year.setText(año);
            BaseDatos.close();


            switch (idPelicula) {
                case 1:
                    img.setImageResource(R.drawable.spiderman);
                    sin.setText("Tras la muerte de sus padres, Peter Parker, un tímido estudiante, vive con su tía May y su tío Ben. Precisamente debido a su retraimiento no es un chico muy popular en el instituto. Un día le muerde una araña que ha sido modificada genéticamente; a la mañana siguiente, descubre estupefacto que posee la fuerza y la agilidad de ese insecto.");
                    break;
                case 2:
                    img.setImageResource(R.drawable.titanic);
                    sin.setText("Jack (DiCaprio), un joven artista, gana en una partida de cartas un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido. A bordo conoce a Rose (Kate Winslet), una joven de una buena familia venida a menos que va a contraer un matrimonio de conveniencia con Cal (Billy Zane), un millonario engreído a quien sólo interesa el prestigioso apellido de su prometida. Jack y Rose se enamoran, pero el prometido y la madre de ella ponen todo tipo de trabas a su relación. Mientras, el gigantesco y lujoso transatlántico se aproxima hacia un inmenso iceberg.");
                    break;
                case 3:
                    img.setImageResource(R.drawable.starwars);
                    sin.setText("muestra la infancia de Darth Vader, el pasado de Obi-Wan Kenobi y el resurgimiento de los Sith, los caballeros Jedi dominados por el Lado Oscuro.");
                    break;
                case 4:
                    img.setImageResource(R.drawable.elhombredeacero);
                    sin.setText("Desde Krypton, un lejano planeta muy avanzado tecnológicamente, un bebé es enviado en una cápsula a través del espacio a la Tierra para que viva entre los humanos. Educado en una granja en Kansas en los valores de sus padres adoptivos, Martha (Diane Lane) y Jonathan Kent (Kevin Costner), el joven Clark Kent (Henry Cavill) comienza desde niño a desarrollar poderes sobrehumanos, y al llegar a la edad adulta llega a la conclusión de que esos poderes le exigen grandes responsabilidades, para proteger no sólo a los que quiere, sino también para representar una esperanza para el mundo.");
                    break;
                case 5:
                    img.setImageResource(R.drawable.jumanji);
                    sin.setText("Alan Parris queda atrapado durante 25 años en un juego de mesa mágico y muy antiguo llamado Jumanji. Cuando, por fin, es liberado por dos niños.");
                    break;
                case 6:
                    img.setImageResource(R.drawable.sinperdon);
                    sin.setText("William Munny (Clint Eastwood) es un pistolero retirado, viudo y padre de familia, que tiene dificultades económicas para sacar adelante a su hijos. Su única salida es hacer un último trabajo.");
                    break;
                case 7:
                    img.setImageResource(R.drawable.matrix);
                    sin.setText("Thomas Anderson es un brillante programador de una respetable compañía de software. Pero fuera del trabajo es Neo, un hacker que un día recibe una misteriosa visita...");
                    break;
            }

        }

    }

        public void volverListadoPelis (View view){
            Intent volver = new Intent(this, MainActivity2.class);
            volver.putExtra("usuario", this.getIntent().getExtras().getString("usuario"));
            startActivity(volver);
        }
    }