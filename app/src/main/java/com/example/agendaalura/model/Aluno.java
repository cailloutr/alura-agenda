package com.example.agendaalura.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {

    private int id = 0;
    private String name;
    private String phone;
    private String email;

    public Aluno(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Aluno() {

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public boolean isIdValid() {
        return id > 0;
    }
}
