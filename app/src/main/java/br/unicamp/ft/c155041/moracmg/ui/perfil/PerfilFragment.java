package br.unicamp.ft.c155041.moracmg.ui.perfil;

import android.os.Bundle;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.unicamp.ft.c155041.moracmg.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;

    public final static String TAG = "PerfilFragment";

    //componentes do Layout
    Spinner spinnerGenero;
    Spinner spinnerCurso;
    EditText txtAno;
    EditText txtDtNascimento;
    EditText txtApelido;
    EditText txtCidadeNatal;
    EditText txtBiografia;
    EditText txtMoradiasAnteriores;
    Button btnSalvar;

    private FirebaseAuth mAuth;
    String uuid;
    FirebaseFirestore db;
    Map<String, Object> userDB = new HashMap<>();



    //TODO fazer os role da foto no storage

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        //TODO prin, não esquece de colocar os listener de spinner aqui ta bom?
        spinnerCurso = view.findViewById(R.id.spinnerCursos);
        spinnerGenero = view.findViewById(R.id.spinnerGenero);
        txtDtNascimento = view.findViewById(R.id.edtTxtDtNascimento);
        txtAno= view.findViewById(R.id.edtTxtUserAnoIngresso);
        txtApelido = view.findViewById(R.id.edtTxtUserNickname);
        txtCidadeNatal = view.findViewById(R.id.edtTxtUserCidadeNatal);
        txtBiografia = view.findViewById(R.id.edtTxtUserBio);
        txtMoradiasAnteriores = view.findViewById(R.id.edtTxtUserMoradiasAnteriores);
        btnSalvar = view.findViewById(R.id.btnSalvarPerfil);

        mAuth = FirebaseAuth.getInstance();
        uuid = mAuth.getUid();
        db = FirebaseFirestore.getInstance();

        return view;
    }

    public void onClickSalvarPerfil (View view){
        String curso = spinnerCurso.getSelectedItem().toString();
            if (curso != null){
                userDB.put("curso",curso);
            }

        String genero = spinnerGenero.getSelectedItem().toString();
            if (genero != null){
                userDB.put("genero",genero);
            }
            //Todo colocar um tipo de campo melhor aqui.... sepa até um outro spinner resolve, texto é muito aberto
            //PErgunta essa fita pro Ulisses, ele vai adorar te ajduar com isso
        String ano = txtAno.getText().toString();
        if (ano != null){
            userDB.put("ano",ano);
        }

        String apelido = txtApelido.getText().toString();
        if (apelido != null){
            userDB.put("apelido,",apelido);
        }

        String cidade = txtCidadeNatal.getText().toString();
        if (cidade != null){
            userDB.put("cidade,",cidade);
        }
        String bio = txtBiografia.getText().toString();
        if (bio != null){
            userDB.put("bio,",bio);
        }
        String moradias = txtMoradiasAnteriores.getText().toString();
        if (moradias != null){
            userDB.put("moradias,",moradias);
        }

        DocumentReference userRef = db.collection("users").document(uuid);

        db.collection("users").document(uuid)
                .set(userDB)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        // Set the "isCapital" field of the city 'DC'
        /*
        userRef.update("capital", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });*/


    }

}