package com.example.alumnos.modelos;

import java.util.Map;

public class Usuario {

    private String id;

    private String email;

    private String name;

    private int role;

    public Usuario(String id, Map<String, Object> document){

        this.id = id;
        email = document.get("email").toString();
        name = document.get("name").toString();
        role = Integer.parseInt(document.get("role").toString());

    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
