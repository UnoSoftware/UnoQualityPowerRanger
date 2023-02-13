package com.example.ps_g8;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Pelicula> lst;

    public CustomAdapter(Context context, List<Pelicula> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView ImageViewPelicula;
        TextView TextViewNombre;
        TextView TextViewDes;
        TextView TextViewSinopsis;
        ImageButton imgbt1, imgbt2;

        Pelicula pelicula = lst.get(i);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.list_item_pelicula, null);

        ImageViewPelicula = view.findViewById(R.id.imageViewPelicula);
        TextViewNombre = view.findViewById(R.id.textViewNombre);
        TextViewDes = view.findViewById(R.id.textViewDes);
        //TextViewSinopsis = view.findViewById(R.id.textViewSinopsis);
        imgbt1 = view.findViewById(R.id.imageButton1);
        imgbt2 = view.findViewById(R.id.imageButton2);
        Button bt = view.findViewById(R.id.button1);

        ImageViewPelicula.setImageResource(pelicula.getImagen());
        TextViewNombre.setText(pelicula.getTitulo());
        TextViewDes.setText(pelicula.getDescripcion());
        //TextViewSinopsis.setText(pelicula.getSinopsis());
        imgbt1.setTag(pelicula);
        imgbt2.setTag(pelicula);
        bt.setTag(pelicula);
        if(pelicula.gusta == 1){
            imgbt1.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        if(pelicula.visto == 1){
            imgbt2.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
        }
        return view;
    }
}

