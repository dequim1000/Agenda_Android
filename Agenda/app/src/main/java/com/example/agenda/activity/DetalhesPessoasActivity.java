package com.example.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.agenda.ContentTransfer;
import com.example.agenda.R;
import com.example.agenda.model.Pessoa;

import java.io.Serializable;

public class DetalhesPessoasActivity extends AppCompatActivity {

    private Pessoa pessoa;
    private TextView nomeText;
    private TextView telefoneText;
    private TextView emailText;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pessoas);
        nomeText = findViewById(R.id.textName);
        telefoneText = findViewById(R.id.textTelefone);
        emailText = findViewById(R.id.textEmail);

        if (getIntent().getSerializableExtra(ContentTransfer.EXTRA_PESSOA) != null) {
            pessoa = (Pessoa) getIntent().getSerializableExtra(ContentTransfer.EXTRA_PESSOA);
            nomeText.setText(pessoa.getNome());
            telefoneText.setText(pessoa.getTelefone());
            emailText.setText(pessoa.getEmail());
        }

        botaoVoltar = findViewById(R.id.btnVoltarCadastrar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalhesPessoasActivity.this.finish();
            }
        });
    }

    //Funções para cada contato
    public void ligar(View v) {
        String tel = "tel:" + pessoa.getTelefone();

        Intent intentTelefone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));

        startActivity(intentTelefone);
    }

    public void mandarMensagem(View v) {
        String tel = pessoa.getTelefone();
        Intent intentMensagem = new Intent(Intent.ACTION_SEND);
        intentMensagem.putExtra(Intent.EXTRA_TEXT, new String[]{tel});
        intentMensagem.setType("text/plain");
        startActivity(Intent.createChooser(intentMensagem, "Escolha um App para enviar a mensagem"));
    }

    public void enviarEmail(View v) {
        String mail = pessoa.getEmail();
        Intent intentEmail = new Intent(Intent.ACTION_SEND);
        intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Mensagem enviada pelo App");
        intentEmail.putExtra(Intent.EXTRA_TEXT, "Mensagem Automática");
        intentEmail.setType("message/rfc822");

        startActivity(Intent.createChooser(intentEmail, "Escolha um App de e-mail"));
    }
}