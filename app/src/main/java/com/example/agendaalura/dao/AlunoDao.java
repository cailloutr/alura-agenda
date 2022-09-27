package com.example.agendaalura.dao;

import com.example.agendaalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {
    //private final String TAG = "AlunoDao";

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;


    public void salvar(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = getAlunoById(aluno);

        if (alunoEncontrado != null) {
            int index = alunos.indexOf(alunoEncontrado);
            alunos.set(index, aluno);
        }
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = getAlunoById(aluno);
        if (alunoDevolvido != null) {
            alunos.remove(aluno);
        }
    }

    private Aluno getAlunoById(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
