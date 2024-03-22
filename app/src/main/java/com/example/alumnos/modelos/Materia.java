package com.example.alumnos.modelos;

import java.util.HashMap;
import java.util.Map;

public class Materia {

    private String id;

    private String nombre;

    private int idImagen;

    private String nivelEducacion;

    private int grado;



    public Materia(String id, Map<String, Object> document){
        this.id = id;
        this.nombre = document.get("nombre").toString();
        this.idImagen = Integer.parseInt( document.get("idImagen").toString() );
        this.nivelEducacion = document.get("nivelEducacion").toString();
        this.grado = Integer.parseInt( document.get("idImagen").toString() );
    }

    public Materia(String nombre, int idImagen, String nivelEducacion, int grado){

        this.nombre = nombre;
        this.idImagen = idImagen;
        this.nivelEducacion = nivelEducacion;
        this.grado = grado;

    }

    public HashMap<String, Object> registrarMap(){

        return new HashMap<String, Object>(){{
            put("nombre", getNombre());
            put("idImagen", getIdImagen());
            put("nivelEducacion",getNivelEducacion());
            put("grado", getGrado());

        }};

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNivelEducacion() {
        return nivelEducacion;
    }

    public void setNivelEducacion(String nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

