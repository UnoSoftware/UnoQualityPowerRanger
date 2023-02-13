package com.example.ps_g8;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DatabaseTest extends TestCase {

    public void testListToString() throws FileNotFoundException {
        Database db= new Database();
        db.cargarListaPelicula();
        String[] s = db.listToString();
    }
}