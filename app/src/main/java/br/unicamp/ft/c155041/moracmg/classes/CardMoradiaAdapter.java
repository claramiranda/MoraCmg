package br.unicamp.ft.c155041.moracmg.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.unicamp.ft.c155041.moracmg.R;

public class CardMoradiaAdapter extends RecyclerView.Adapter <CardMoradiaAdapter.MoradiaViewHolder>{
    private Context myContext;
    private List<Moradia> moradiaList;


    //Implementação da classe interna de viewholder
    class MoradiaViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoMoradia;
        TextView txtNomeMoradia;
        TextView txtQtdMoradores;
        TextView txtAgitacao;
        TextView txtGeneroMoradia;
        TextView txtTipoMoradia;
        TextView txtBioMoradia;
        TextView txtReferenciaMoradia;
        Button btnPerfilMoradia;

        //construtor com super obrigatório
        public MoradiaViewHolder(@NonNull View view) {
            super(view);

            fotoMoradia = view.findViewById(R.id.foto_moradia);
            txtNomeMoradia = view.findViewById(R.id.txt_nome_card_moradia);
            txtQtdMoradores  = view.findViewById(R.id.txt_qtd_moradores_card_moradia);
            txtAgitacao  = view.findViewById(R.id.txt_agitacao_card_moradia);
            txtGeneroMoradia  = view.findViewById(R.id.txt_genero_card_moradia);
            txtTipoMoradia = view.findViewById(R.id.txt_tipo_card_moradia);
            txtBioMoradia = view.findViewById(R.id.txt_bio_card_moradia);
            txtReferenciaMoradia = view.findViewById(R.id.txt_referencia_localizacao_card_moradia);

        }
    }



    //Daqui pra baixo é tudo de implementação obrigatória
    @NonNull
    @Override
    public MoradiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //método que infla o layout de cada card
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View view = inflater.inflate(R.layout.layout_cardview_moradias, null);

        //retorna viewholder
        return new MoradiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoradiaViewHolder holder, int position) {
        Moradia moradia = moradiaList.get(position);

        holder.txtNomeMoradia.setText(moradia.getMoradiaNome());

        String qtdMoradores = moradia.getQtdMoradores() + " moradores";
        holder.txtQtdMoradores.setText(qtdMoradores);

        String lvlAgitacao = "Nivel de Agitação: " + moradia.getNivelAgitacao();
        holder.txtAgitacao.setText(lvlAgitacao);

        holder.txtGeneroMoradia.setText(moradia.getGenero());

        String tipoMoradia = "Tipo: " + moradia.getMoradiaTipo();
        holder.txtTipoMoradia.setText(tipoMoradia);

        //aqui dá pra colocar um tratamento de qtd de caracteres
        String bioMoradia = moradia.getMoradiaDesc();
        holder.txtBioMoradia.setText(bioMoradia);

        holder.txtReferenciaMoradia.setText(moradia.getMoradiaReferencia());

        holder.fotoMoradia.setImageDrawable(myContext.getResources().getDrawable(moradia.getMoradiaFoto()));
    }


    @Override
    public int getItemCount() {
        return moradiaList.size();
    }

    //Construtor
    public CardMoradiaAdapter(Context myContext, List<Moradia> moradiaList) {
        this.myContext = myContext;
        this.moradiaList = moradiaList;
    }
}
