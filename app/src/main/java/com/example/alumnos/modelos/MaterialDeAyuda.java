package com.example.alumnos.modelos;

import java.util.HashMap;
import java.util.Map;

public class MaterialDeAyuda {

    private String id;

    private String link;

    private String tema;

    private  String materia;

    private String nivelEducacion;

    private int grado;

    public MaterialDeAyuda(String id, Map<String, Object> document){
        this.id = id;
        this.link =  document.get("link").toString();
        this.tema = document.get("tema").toString();
        this.materia = document.get("materia").toString();
        this.nivelEducacion = document.get("nivelEducacion").toString();
        this.grado = Integer.parseInt(document.get("grado").toString()) ;
    }

    public MaterialDeAyuda(String id,String link, String tema, String materia, String nivelEducacion, int grado){
        this.id = id;
        this.link = link;
        this.tema = tema;
        this.materia = materia;
        this.nivelEducacion = nivelEducacion;
        this.grado =grado;
    }

    public MaterialDeAyuda(String link, String tema, String materia,String nivelEducacion, int grado){
        this.link = link;
        this.tema = tema;
        this.materia = materia;
        this.nivelEducacion = nivelEducacion;
        this.grado =grado;
    }

    public String getNivelEducacion() {
        return nivelEducacion;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public void setNivelEducacion(String nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getMateria() {
        return materia;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public HashMap<String, Object> registrarMap(){

        return new HashMap<String, Object>(){{
            put("link", getLink());
            put("tema", getTema());
            put("materia", getMateria());
            put("nivelEducacion",getNivelEducacion());
            put("grado", getGrado());

        }};

    }
}
