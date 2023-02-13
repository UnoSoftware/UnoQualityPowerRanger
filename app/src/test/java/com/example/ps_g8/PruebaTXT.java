package com.example.ps_g8;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PruebaTXT {
    @Test
    public void probarFichero() throws FileNotFoundException {
        Database db= new Database();
        ArrayList<Pelicula> peli = new ArrayList<Pelicula>();
        db.cargarListaPelicula();
        peli = db.getListP();
        System.out.println(peli.get(1).getTitulo());
        System.out.println(peli.get(2).getTitulo());
        assertEquals(peli, db.getListP());
    }
}
