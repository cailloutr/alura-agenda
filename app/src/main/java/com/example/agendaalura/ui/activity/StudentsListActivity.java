package com.example.agendaalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaalura.R;
import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {

    public static final String TAG = "StudentsListActivity";
    public final String NOVO_ALUNO = "Novo aluno";
    private FloatingActionButton fabAddNewStudent;
    private final AlunoDao alunoDao = new AlunoDao();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studdents_list);
        setTitle(NOVO_ALUNO);

        inicializarComponentes();

        configurarNovoAlunoFabClickListenner();
        alunoDao.salvar(new Aluno("Caio", "2233445566", "caio.trocilo@gmail.com"));
        alunoDao.salvar(new Aluno("Daniel", "2233445566", "daniel.trocilo@gmail.com"));

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

        ListView studentList = findViewById(R.id.activity_students_listview);
        List<Aluno> alunos = alunoDao.todos();
        studentList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "Posição: " + alunos.get(i));

                Aluno aluno = alunos.get(i);
                Intent intent = new Intent(StudentsListActivity.this, StudentFormActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);

            }
        });
    }
}
