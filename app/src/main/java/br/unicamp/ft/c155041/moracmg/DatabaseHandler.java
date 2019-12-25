package br.unicamp.ft.c155041.moracmg;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {

    public final static String TAG = "DB_Handler";

    Usuario usuario = new Usuario();

    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    Map<String, Object> userDB = new HashMap<>();


    public DatabaseHandler() {

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }


    public void saveUserOnDatabase(Usuario user) {
        String uuid = mAuth.getUid();
        DocumentReference userRef = db.collection("users").document(uuid);

        userDB.put("nome", user.getNome());
        userDB.put("apelido", user.getApelido());
        userDB.put("dt_nascimento", user.getDt_nascimento());
        userDB.put("curso", user.getCurso());
        userDB.put("genero", user.getGenero());
        userDB.put("ano_ingresso", user.getAno_ingresso());
        userDB.put("biografia", user.getBiografia());
        userDB.put("moradias_anteriores", user.getMoradias_anteriores());
        userDB.put("cidade_natal", user.getCidade_natal());
        userDB.put("ra", user.getRa());

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

        userRef.update(userDB);
    }

    public void getUserFromDatabase(String uuid) {

        DocumentReference docRef = db.collection("users").document(uuid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        //TODO alimentar atributo usuario
                        // Certo, como eu como eu faço agora pra ter acesso aos valores de cada campo desse document?

                        //usuario.setRa(document.get("nome"));
                        Usuario user = document.toObject(Usuario.class);
                        setUsuario(user);
                        printUserOnLog(usuario);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        //r
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


     public void printUserOnLog(Usuario user){
         Log.d(TAG, "user.nome=" + user.getNome());
         Log.d(TAG, "user.curso=" + user.getCurso());
         Log.d(TAG, "user.bio=" + user.getBiografia());
         Log.d(TAG, "user.moradias=" + user.getMoradias_anteriores());
         Log.d(TAG, "user.cidade=" + user.getCidade_natal());
     }

}