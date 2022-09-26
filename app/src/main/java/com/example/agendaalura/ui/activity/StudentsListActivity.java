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
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaalura.R;
import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;
import com.example.agendaalura.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity implements ConstantesActivities {

    public static final String TAG = "StudentsListActivity";
    public final String NOVO_ALUNO = "Novo aluno";
    private FloatingActionButton fabAddNewStudent;
    private final AlunoDao alunoDao = new AlunoDao();
    private ListaAlunosAdapter adapter;
    //private ArrayAdapter<Aluno> adapter;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remover(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void remover(Aluno alunoEscolhido) {
        alunoDao.remove(alunoEscolhido);
        //adapter.remove(alunoEscolhido);
        atualizaLista();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studdents_list);
        setTitle(NOVO_ALUNO);

        inicializarComponentes();
        inicializaListaDeAlunos();

        configurarNovoAlunoFabClickListenner();
        alunoDao.salvar(new Aluno("Caio", "2233445566", "caio.trocilo@gmail.com"));
        alunoDao.salvar(new Aluno("Daniel", "2233445566", "daniel.trocilo@gmail.com"));
        alunoDao.salvar(new Aluno("Daniel", "2233445566", "daniel.trocilo@gmail.com"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    private void atualizaLista() {
        adapter.clear();
        adapter.addAll(alunoDao.todos());
    }

    private void configurarNovoAlunoFabClickListenner() {
        fabAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciaAtividadeDeFormulario(new Intent(
                        StudentsListActivity.this, StudentFormActivity.class));
            }
        });
    }

    private void iniciaAtividadeDeFormulario(Intent intent) {
        startActivity(intent);
    }

    private void inicializarComponentes() {
        fabAddNewStudent = findViewById(R.id.activity_students_list_fab);
    }

    private void inicializaListaDeAlunos() {
        ListView studentList = findViewById(R.id.activity_students_listview);
        List<Aluno> alunos = alunoDao.todos();
        configuraAdapter(studentList);

        configuraOnItemClickListenerParaCadaItem(studentList);
        registerForContextMenu(studentList);
    }


    private void configuraOnItemClickListenerParaCadaItem(ListView studentList) {
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(StudentsListActivity.this, StudentFormActivity.class);
                intent.putExtra(CHAVE_ALUNO, aluno);
                iniciaAtividadeDeFormulario(intent);
            }
        });
    }


    private void configuraAdapter(ListView studentList) {
        adapter = new ListaAlunosAdapter(this);
        studentList.setAdapter(adapter);
    }
}
