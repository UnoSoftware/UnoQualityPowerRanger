package com.example.ps_g8;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity{

    private ListView lv1;
    private List<Pelicula> lst;
    private ImageButton tgbtn1;
    private ImageButton tgbtn2;
    private Button btn1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv1 = (ListView) findViewById(R.id.lv1);
        CustomAdapter adapter=new CustomAdapter(this, GetData());
        lv1.setAdapter(adapter);
    }

    public void me_gusta (View view) {
        tgbtn1 = view.findViewById(R.id.imageButton1);
        Pelicula p = (Pelicula) tgbtn1.getTag();
        int id = p.getId();
        String email = getIntent().getExtras().getString("usuario");
        changeBoolean(id, email,0, tgbtn1);
        //Toast.makeText(getApplicationContext(), Integer.toString(p.getId()), Toast.LENGTH_SHORT).show();
    }

    public void visto (View view) {
        tgbtn2 = view.findViewById(R.id.imageButton2);
        Pelicula p = (Pelicula) tgbtn2.getTag();
        int id = p.getId();
        String email = getIntent().getExtras().getString("usuario");
        changeBoolean(id, email,1, tgbtn2);
        //Toast.makeText(getApplicationContext(), Integer.toString(p.getId()), Toast.LENGTH_SHORT).show();
    }

    public void mas_detalles (View view) {
        btn1 = view.findViewById(R.id.button1);
        Pelicula p = (Pelicula) btn1.getTag();
        int id = p.getId();
        String email = getIntent().getExtras().getString("usuario");
        Intent start6 = new Intent(this, MainActivity6.class);
        start6.putExtra("pelicula", id);
        start6.putExtra("usuario", email);
        startActivity(start6);
    }

    @SuppressLint("Range")
    public void changeBoolean(int id, String email, int tipo, ImageButton tgbtn){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Cursor c = BaseDatos.rawQuery("select * from relacion where id =" + id + " and usuario =?", new String[]{getIntent().getExtras().getString("usuario")});
        ContentValues cv = new ContentValues();
        cv.put("usuario", email);
        cv.put("id", id);

        if (c.moveToFirst()) {
            if (tipo == 1) {
                cv.put("gusta", c.getInt(c.getColumnIndex("gusta")));
                if (c.getInt(c.getColumnIndex("visto")) == 0) {
                    cv.put("visto", 1);
                    tgbtn.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                }
                else {
                    cv.put("visto", 0);
                    tgbtn.setImageResource(R.drawable.ic_baseline_not_interested_24);
                }
            }

            else if (tipo == 0) {
                cv.put("visto", c.getInt(c.getColumnIndex("visto")));
                if (c.getInt(c.getColumnIndex("gusta")) == 0) {
                    cv.put("gusta", 1);
                    tgbtn.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
                else {
                    cv.put("gusta", 0);
                    tgbtn.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }

            BaseDatos.update("relacion", cv, "id =" + id + " and usuario =?", new String[]{getIntent().getExtras().getString("usuario")});
        }


        c.close();
        BaseDatos.close();
    }


    @SuppressLint("Range")
    public List<Pelicula> GetData() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        lst = new ArrayList<>();
        Cursor c = BaseDatos.rawQuery("select * from pelicula", null);
        if (c.moveToFirst() && c.getCount() >= 1) {
            do {
                int gusta = 0;
                int visto = 0;
                @SuppressLint("Range") String titulo = c.getString(c.getColumnIndex("nombre"));
                @SuppressLint("Range") String sinopsis = c.getString(c.getColumnIndex("sinopsis"));
                @SuppressLint("Range") String año = c.getString(c.getColumnIndex("año"));
                @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
                Cursor c2 = BaseDatos.rawQuery("select * from relacion where id =" + id + " and usuario =?", new String[]{getIntent().getExtras().getString("usuario")});
                if(c2.moveToFirst() && c2.getCount() >= 1){
                    gusta = c2.getInt(c2.getColumnIndex("gusta"));
                    visto = c2.getInt(c2.getColumnIndex("visto"));
                }

                switch (id){
                    case 1:lst.add(new Pelicula(id, R.drawable.spiderman, titulo, año,  sinopsis, gusta, visto));
                    break;
                    case 2:lst.add(new Pelicula(id, R.drawable.titanic, titulo, año, sinopsis ,gusta, visto));
                    break;
                    case 3:lst.add(new Pelicula(id, R.drawable.starwars, titulo, año, sinopsis, gusta, visto));
                    break;
                    case 4:lst.add(new Pelicula(id, R.drawable.elhombredeacero, titulo, año, sinopsis, gusta, visto));
                    break;
                    case 5:lst.add(new Pelicula(id, R.drawable.jumanji, titulo, año, sinopsis, gusta, visto));
                    break;
                    case 6:lst.add(new Pelicula(id, R.drawable.sinperdon, titulo, año, sinopsis, gusta, visto));
                    break;
                    case 7:lst.add(new Pelicula(id, R.drawable.matrix, titulo, año, sinopsis, gusta, visto));
                    break;
                }
                c2.close();
            } while (c.moveToNext());
        }
        c.close();
        BaseDatos.close();
        return lst;
    }

    @Override public boolean onCreateOptionsMenu(Menu menuOpciones) {

        getMenuInflater().inflate(R.menu.acordeon_opciones, menuOpciones);

        return true;
    }
    
    @Override public boolean onOptionsItemSelected(MenuItem opcion_Menu){
        int id= opcion_Menu.getItemId();
        if (id == R.id.logout){
            Intent start1 = new Intent(this, MainActivity.class);
            startActivity(start1);
            return true;
        }
        if (id == R.id.borrarCuenta){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to delete your account?");
            builder.setTitle("Delete Account");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                @Override
                public void onClick (DialogInterface dialog, int which){
                    borrarCuenta();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                @Override
                public void onClick (DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(opcion_Menu);
    }

    public void borrarCuenta(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        BaseDatos.delete("usuario", "email =?", new String[]{getIntent().getExtras().getString("usuario")});
        BaseDatos.close();
        Intent start1 = new Intent(this,MainActivity.class);
        startActivity(start1);
    }

}

