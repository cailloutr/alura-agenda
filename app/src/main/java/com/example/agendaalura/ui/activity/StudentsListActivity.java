package com.example.agendaalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaalura.R;
import com.example.agendaalura.dao.AlunoDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsListActivity extends AppCompatActivity {

    public final String NOVO_ALUNO = getString(R.string.novo_aluno_title);
    private FloatingActionButton fabAddNewStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studdents_list);
        setTitle(NOVO_ALUNO);

        inicializarComponentes();

        configurarNovoAlunoFabClickListenner();
    }

    @Override
    protected void onResume() {
        super.onResume();

        inicializaListaDeAlunos();
    }

    private void configurarNovoAlunoFabClickListenner() {
        fabAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                        StudentsListActivity.this, StudentFormActivity.class));
            }
        });
    }

    private void inicializarComponentes() {
        fabAddNewStudent = findViewById(R.id.activity_students_list_fab);
    }

    private void inicializaListaDeAlunos() {
        AlunoDao alunoDao = new AlunoDao();

        ListView studentList = findViewById(R.id.activity_students_listview);
        studentList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDao.todos()));
    }
}
