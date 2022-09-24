package com.example.agendaalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaalura.R;
import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;

public class StudentFormActivity extends AppCompatActivity implements ConstantesActivities {

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    AlunoDao alunoDao = new AlunoDao();
    private Button saveButton;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        inicializarComponentes();

        configurarSaveButtonClickListenner();

        Intent intent = getIntent();
        carregaAluno(intent);
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

    private void configurarSaveButtonClickListenner() {
        saveButton.setOnClickListener(view -> {
            preencheAluno(aluno);
            if (aluno.isIdValid()) {
                alunoDao.edita(aluno);
            } else {
                alunoDao.salvar(aluno);
            }
            finish();
        });
    }

    private void inicializarComponentes() {
        saveButton = findViewById(R.id.activity_students_form_save_button);
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