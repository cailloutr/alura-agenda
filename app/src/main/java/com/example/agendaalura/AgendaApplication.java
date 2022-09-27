package com.example.agendaalura;

import android.app.Application;

import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDao alunoDao = new AlunoDao();
        alunoDao.salvar(new Aluno("Caio", "2233445566", "caio.trocilo@gmail.com"));
        alunoDao.salvar(new Aluno("Daniel", "2233445566", "daniel.trocilo@gmail.com"));
        alunoDao.salvar(new Aluno("Daniel", "2233445566", "daniel.trocilo@gmail.com"));
    }
}
