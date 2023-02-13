package com.example.ps_g8;

public class Pelicula {

    private int id;
    private int imagen;
    private String titulo;
    private String descripcion;
    private String sinopsis;
    int gusta;
    int visto;



    public Pelicula(int id, int imagen, String titulo, String descripcion, String sinopsis, int gusta, int visto){
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sinopsis=sinopsis;
        this.gusta=gusta;
        this.visto=visto;

    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public String getSinopsis() {

        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setDescripcion(String genero) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getId(){
        return id;
    }
}
