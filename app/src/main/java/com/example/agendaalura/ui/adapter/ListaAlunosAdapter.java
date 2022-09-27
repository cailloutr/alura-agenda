package com.example.agendaalura.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agendaalura.R;
import com.example.agendaalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CommentedOutCode")
public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);

        Aluno aluno = alunos.get(i);
        TextView name = viewCriada.findViewById(R.id.item_aluno_name);
        name.setText(aluno.getName());

        TextView phone = viewCriada.findViewById(R.id.item_aluno_phone);
        phone.setText(aluno.getPhone());
        return viewCriada;
    }


    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

//    public void remove(Aluno aluno) {
//        alunos.remove(aluno);
//        notifyDataSetChanged();
//    }
}
