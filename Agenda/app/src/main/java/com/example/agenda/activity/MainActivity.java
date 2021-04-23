package com.example.agenda.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.example.agenda.ContentTransfer;
import com.example.agenda.R;
import com.example.agenda.adapter.PessoaAdapter;
import com.example.agenda.model.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PessoaAdapter.OnItemClickListener{

    private List<Pessoa> pessoas = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criandoAgenda();

        //Cria o recycler e adapter e aponta
        recyclerView = findViewById(R.id.pessoas_recyclerView);
        //define o tamanho
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PessoaAdapter(pessoas, this);
        recyclerView.setAdapter(adapter);


        //Botão de Add - Bolinha
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastrar = new Intent(getApplicationContext(), CadastrarActivity.class);
                startActivityForResult(intentCadastrar, ContentTransfer.REQUEST_CADASTROS); //Quando retorna algum dado eu utilizo a startActivityForResult para ir para a Intent já esperando receber algum retorno, no caso ai ele recebe o Content
            }
        });
    }

    @Override //Metodo que espera receber um codigo de retorono, junto com uma intent
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case ContentTransfer.REQUEST_CADASTROS: //Ai caso o retorno foi igual a 1
                if(resultCode == RESULT_OK) //Ele ele for igual o RESULT_OK (Pre definido por padrão)
                {   //Ele add na lista pessoa o Intent q pega o metodo SeriazalizableExtra que é implementado na Classe Pessoa e passa o Content EXTRA_PESSOA, que é uma Key para identificar o objeto passado
                    pessoas.add((Pessoa) data.getSerializableExtra(ContentTransfer.EXTRA_PESSOA));
                    adapter.notifyItemInserted(pessoas.size() - 1); //Aqui notifica o Adapter que foi inserido um item na ultima posição
                }
                break;
        }

    }

    private void criandoAgenda(){
        pessoas.add(new Pessoa("André Otávio", "982122502", "dequim1000@gmail.com"));
        pessoas.add(new Pessoa("José Naldo", "981232123", "josinho@gmail.com"));
        pessoas.add(new Pessoa("Diogo", "12312312321", "diogo123@hotmail.com"));
        pessoas.add(new Pessoa("Gabriela Rocha", "123654789", "gabi@gmail.com"));
        pessoas.add(new Pessoa("Diego Santos", "789456123", "diego@gmail.com"));
        pessoas.add(new Pessoa("Neymar Jr.", "456987123", "neyday@gmail.com"));
        pessoas.add(new Pessoa("Mbbappé", "159951753", "mbappee@gmail.com"));
        pessoas.add(new Pessoa("Cleitim ", "753951123", "clayton@gmail.com"));
        pessoas.add(new Pessoa("Ronaldo", "1425369685", "ronaldim@gmail.com"));
        pessoas.add(new Pessoa("Weverton", "1542623515", "campeao@gmail.com"));
        pessoas.add(new Pessoa("Henrique", "7595486684", "rick@gmail.com"));
        pessoas.add(new Pessoa("Gideone", "759684759", "gidgod@gmail.com"));
    }

    @Override //Quando não for retornado nenhum objeto, não preciso me preucupar com o Intent diferente, e posso passar somente a intent que vai ser chamada
    public void onItemClick(int position) { //Clicar em cada item
        //Abrir uma tela nova
        Intent intent = new Intent(this, DetalhesPessoasActivity.class);
        //Passa para a tela os dados - PRECISA NO MODEL PESSOA DE UM Serializable
        intent.putExtra(ContentTransfer.EXTRA_PESSOA, pessoas.get(position));
        //STARTA A TELA
        startActivity(intent);
    }
}

