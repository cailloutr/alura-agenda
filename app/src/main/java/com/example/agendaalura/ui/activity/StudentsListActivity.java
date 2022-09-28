package com.example.agendaalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaalura.R;
import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;
import com.example.agendaalura.ui.StudentsListView;
import com.example.agendaalura.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsListActivity extends AppCompatActivity implements ConstantesActivities {

    public final String NOVO_ALUNO = "Novo aluno";
    private FloatingActionButton fabAddNewStudent;
    private final StudentsListView studentsListView = new StudentsListView(this);

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            studentsListView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studdents_list);
        setTitle(NOVO_ALUNO);

        inicializarComponentes();
        inicializaListaDeAlunos();

        configurarNovoAlunoFabClickListenner();

    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsListView.atualizaLista();
    }

    private void configurarNovoAlunoFabClickListenner() {
        fabAddNewStudent.setOnClickListener(view -> iniciaAtividadeDeFormulario(new Intent(
                StudentsListActivity.this, StudentFormActivity.class)));
    }

    private void iniciaAtividadeDeFormulario(Intent intent) {
        startActivity(intent);
    }

    private void inicializarComponentes() {
        fabAddNewStudent = findViewById(R.id.activity_students_list_fab);
    }

    private void inicializaListaDeAlunos() {
        ListView studentList = findViewById(R.id.activity_students_listview);
        studentsListView.configuraAdapter(studentList);

        configuraOnItemClickListenerParaCadaItem(studentList);
        registerForContextMenu(studentList);
    }


    private void configuraOnItemClickListenerParaCadaItem(ListView studentList) {
        studentList.setOnItemClickListener((adapterView, view, i, l) -> {
            Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(StudentsListActivity.this, StudentFormActivity.class);
            intent.putExtra(CHAVE_ALUNO, aluno);
            iniciaAtividadeDeFormulario(intent);
        });
    }
}
