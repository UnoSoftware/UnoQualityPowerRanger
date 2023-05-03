package com.example.ps_g8;

import org.junit.Test;
import static org.junit.Assert.*;


/*public String getTitulo() {
        return titulo;
}*/

/*public void setTitulo(String titulo) {
        this.titulo = titulo;
}*/

/*public String getDescripcion() {
        return descripcion;
}*/

/*public String getSinopsis() {
        return sinopsis;
}*/

public class UnitTest {
    @Test
    public void testgetTitulo() {
        Pelicula peli = new Pelicula(1, 1, "Esto es un título", "Esto es una descripción", "Esto es una sinopsis", 0, 0);
        String tit = "Esto es un título";
        String result = peli.getTitulo();
        assertEquals(tit, result);
    }


    @Test
    public void testsetTitulo() {
        Pelicula peli = new Pelicula(1, 1, "Esto es un título", "Esto es una descripción", "Esto es una sinopsis", 0, 0);
        String tit = "Labella y la bestia";
        peli.setTitulo(tit);
        String result = peli.getTitulo();
        assertEquals(tit, result);
    }


    @Test
    public void testgetDescripcion() {
        Pelicula peli = new Pelicula(1, 1, "Esto es un título", "Esto es una descripción", "Esto es una sinopsis", 0, 0);
        String des = "Esto es una descripción";
        String result = peli.getDescripcion();
        assertEquals(des, result);
    }


    @Test
    public void testsetDescripcion() {
        Pelicula peli = new Pelicula(1, 1, "Esto es un título", "Esto es una descripción", "Esto es una sinopsis", 0, 0);
        String des = "Descipcion descrita de forma describida";
        peli.setDescripcion(des);
        String result = peli.getDescripcion();
        assertEquals(des, result);
    }
}
