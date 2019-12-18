package br.unicamp.ft.c155041.moracmg.ui.perfil;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.unicamp.ft.c155041.moracmg.DatabaseHandler;
import br.unicamp.ft.c155041.moracmg.R;
import br.unicamp.ft.c155041.moracmg.Usuario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;

    public final static String TAG = "PerfilFragment";

    Usuario usuario;

    //componentes do Layout
    Spinner spinnerGenero;
    Spinner spinnerCurso;
    Spinner spinnerAno;

    EditText txtNome;
    EditText txtApelido;
    EditText txtDtNascimento;

    EditText txtBiografia;
    EditText txtMoradiasAnteriores;
    EditText txtCidadeNatal;

    Button btnSalvar;

    DatabaseHandler dbHandler = new DatabaseHandler();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uuid = mAuth.getUid();


    //TODO fazer os role da foto no storage
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);


        spinnerCurso = view.findViewById(R.id.spinnerCursos);
        spinnerGenero = view.findViewById(R.id.spinnerGenero);
        spinnerAno = view.findViewById(R.id.spinnerAno);

        txtDtNascimento = view.findViewById(R.id.edtTxtDtNascimento);
        txtNome= view.findViewById(R.id.edtTxtUserNome);
        txtApelido = view.findViewById(R.id.edtTxtUserNickname);

        txtCidadeNatal = view.findViewById(R.id.edtTxtUserCidadeNatal);
        txtBiografia = view.findViewById(R.id.edtTxtUserBio);
        txtMoradiasAnteriores = view.findViewById(R.id.edtTxtUserMoradiasAnteriores);

        btnSalvar = view.findViewById(R.id.btnSalvarPerfil);
        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BotaoSalvarUsuario(v);
            }
        });

        dbHandler.getUserFromDatabase(uuid);

        return view;
    }

    public void BotaoSalvarUsuario(View view){

        String curso = spinnerCurso.getSelectedItem().toString();
        String genero = spinnerGenero.getSelectedItem().toString();
        String ano = spinnerAno.getSelectedItem().toString();

        String nome = txtNome.getText().toString();
        if(TextUtils.isEmpty(nome)) {
            txtNome.setError("Campo obrigarório não informado.");
            return;
        }

        String apelido = txtApelido.getText().toString();
/*        if(TextUtils.isEmpty(apelido)) {
            //apelido = "Não informado";
            txtApelido.setError("Não");
            return;
        }*/

        String cidade = txtCidadeNatal.getText().toString();
/*        if(TextUtils.isEmpty(cidade)) {
            cidade = "Não informado";
        }*/

        String bio = txtBiografia.getText().toString();
/*        if(TextUtils.isEmpty(bio)) {
            bio = "";

        }*/



        String moradias = txtMoradiasAnteriores.getText().toString();
/*        if(TextUtils.isEmpty(moradias)) {
            txtMoradiasAnteriores.setError("Campo não informado.");
            return;
        }*/


        String dt_nascimento = txtDtNascimento.getText().toString();
        if(TextUtils.isEmpty(dt_nascimento)) {
            txtDtNascimento.setError("Campo obrigatório não informado.");
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

         usuario = new Usuario(nome, email, curso,  genero,
                dt_nascimento,  ano,  apelido,  cidade,
                bio,  moradias);


        dbHandler.saveUserOnDatabase(usuario);

        //Atualizar tela
        //Valores atuais do campo são setados como Hint
        dbHandler.getUserFromDatabase(uuid);
        carregarDadosUsuario();
    }

    public void carregarDadosUsuario(){

        if(usuario.getNome() != "" ){
            //txtNome.setText(usuario.getNome());
            txtNome.setText("");
            txtNome.setHint(usuario.getNome());
        }

        if(usuario.getApelido() != ""){
            txtApelido.setText("");
            txtApelido.setHint(usuario.getApelido());
        }

        if(usuario.getMoradiasAnteriores() != ""){
            txtMoradiasAnteriores.setText("");
            txtMoradiasAnteriores.setHint(usuario.getMoradiasAnteriores());
        }
        txtDtNascimento.setText("");
        txtDtNascimento.setHint(usuario.getDt_nascimento());

        if(usuario.getBio() != ""){
            txtBiografia.setText("");
            txtBiografia.setHint(usuario.getBio());
        }

        if(usuario.getCidade_natal() != ""){
            txtCidadeNatal.setText("");
            txtCidadeNatal.setHint(usuario.getCidade_natal());
        }


    }

}