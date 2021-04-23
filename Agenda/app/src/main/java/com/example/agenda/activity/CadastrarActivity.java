package com.example.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.ContentTransfer;
import com.example.agenda.R;
import com.example.agenda.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class CadastrarActivity extends AppCompatActivity {

    private List<Pessoa> pessoas = new ArrayList<>();
    private Button btnVoltar, btnSalvar;
    private EditText textNome, textTelefone, textEmail;
    private String nome, telefone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        inicializarVariaveis();
        incicializarMetodos();

    }

    private void inicializarVariaveis(){
        textNome = findViewById(R.id.textNome);
        textTelefone = findViewById(R.id.textTelefone);
        textEmail = findViewById(R.id.textEmail);
        btnVoltar = findViewById(R.id.btnVoltarCadastrar);
        btnSalvar = findViewById(R.id.btnSalvar);
    }

    private void incicializarMetodos(){
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = textNome.getText().toString();
                telefone = textTelefone.getText().toString();
                email = textEmail.getText().toString();

                Toast.makeText(CadastrarActivity.this, "Usuário cadastrado", Toast.LENGTH_SHORT).show();
                //
                Intent data = new Intent();
                data.putExtra(ContentTransfer.EXTRA_PESSOA, new Pessoa(nome, telefone, email)); //Passa a Key e o Objeto para a outra tela usando o putExtra (Necessita do Serialazeble
                //
                setResult(RESULT_OK, data);
                CadastrarActivity.this.finish();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarActivity.this.finish(); //Finaliza a intent atual
            }
        });
    }




}