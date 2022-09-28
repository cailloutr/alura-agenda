package com.example.agendaalura.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;
import com.example.agendaalura.ui.adapter.ListaAlunosAdapter;

public class StudentsListView {

    private final Context context;
    private final ListaAlunosAdapter adapter;
    private final AlunoDao alunoDao;

    public StudentsListView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(context);
        this.alunoDao = new AlunoDao();
    }

    public void confirmaRemocao(@NonNull MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que deseja continuar?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remover(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void remover(Aluno alunoEscolhido) {
        alunoDao.remove(alunoEscolhido);
        //adapter.remove(alunoEscolhido);
        atualizaLista();
    }

    public void atualizaLista() {
        adapter.atualiza(alunoDao.todos());
    }

    public void configuraAdapter(ListView studentList) {
        studentList.setAdapter(adapter);
    }
}
