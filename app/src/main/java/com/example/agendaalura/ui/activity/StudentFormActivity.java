package com.example.agendaalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaalura.R;
import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;

public class StudentFormActivity extends AppCompatActivity implements ConstantesActivities {

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    AlunoDao alunoDao = new AlunoDao();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        inicializarComponentes();

        Intent intent = getIntent();
        carregaAluno(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_students_form_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_students_form_salvar) {
            salvarAluno();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno(Intent intent) {
        if (intent.hasExtra(CHAVE_ALUNO)) {
            aluno = (Aluno) intent.getSerializableExtra(CHAVE_ALUNO);
            setTitle(CHAVE_EDITER_ALUNO);
            preencheEditsTexts();
        } else {
            setTitle(CHAVE_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheEditsTexts() {
        edtName.setText(aluno.getName());
        edtPhone.setText(aluno.getPhone());
        edtEmail.setText(aluno.getEmail());
    }

    private void salvarAluno() {
        preencheAluno(aluno);
        if (aluno.isIdValid()) {
            alunoDao.edita(aluno);
        } else {
            alunoDao.salvar(aluno);
        }
        finish();
    }

    private void inicializarComponentes() {
        edtName = findViewById(R.id.activity_students_form_name);
        edtPhone = findViewById(R.id.activity_students_form_phone);
        edtEmail = findViewById(R.id.activity_students_form_email);
    }

    private void preencheAluno(Aluno aluno) {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();

        aluno.setName(name);
        aluno.setEmail(email);
        aluno.setPhone(phone);
    }
}