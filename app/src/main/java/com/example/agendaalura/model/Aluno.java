package com.example.agendaalura.model;

import androidx.annotation.NonNull;

public class Aluno {
    private final String name;
    private final String phone;
    private final String email;

    public Aluno(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
