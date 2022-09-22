package com.example.agendaalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaalura.R;
import com.example.agendaalura.dao.AlunoDao;
import com.example.agendaalura.model.Aluno;

import java.io.Serializable;

public class StudentFormActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;

    AlunoDao alunoDao = new AlunoDao();
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        inicializarComponentes();

        configurarSaveButtonClickListenner();

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        edtName.setText(aluno.getName());
        edtPhone.setText(aluno.getPhone());
        edtEmail.setText(aluno.getEmail());
    }

    private void configurarSaveButtonClickListenner() {
        saveButton.setOnClickListener(view -> {
            salvarAluno();
            finish();
        });
    }

    private void inicializarComponentes() {
        saveButton = findViewById(R.id.activity_students_form_save_button);
        edtName = findViewById(R.id.activity_students_form_name);
        edtPhone = findViewById(R.id.activity_students_form_phone);
        edtEmail = findViewById(R.id.activity_students_form_email);
    }

    private void salvarAluno() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();

        Aluno aluno = new Aluno(name, phone, email);

        alunoDao.salvar(aluno);
    }
}