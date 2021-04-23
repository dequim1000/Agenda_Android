package com.example.agenda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;
import com.example.agenda.model.Pessoa;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {

    private List<Pessoa> pessoas;
    private OnItemClickListener listener;

    public PessoaAdapter(List<Pessoa> pessoas, OnItemClickListener listener) {
        this.pessoas = pessoas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PessoaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRecyclerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaAdapter.MyViewHolder holder, int position) {
        Pessoa itemPessoa = pessoas.get(position);
        //holder.imageView.setImageDrawable(R.drawable.ic_baseline_people_24);
        holder.name_textView.setText(itemPessoa.getNome());
    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name_textView;
        //public TextView cod_Peca;
        //public TextView Preco_Peca;
        //public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textView = itemView.findViewById(R.id.name_textView);
            //imageView = itemView.findViewById(R.id.imagemLogo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
}
