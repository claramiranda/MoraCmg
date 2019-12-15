package br.unicamp.ft.c155041.moracmg;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {

    public final static String TAG = "DB_Handler";
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    Map<String, Object> userDB = new HashMap<>();


    public DatabaseHandler() {

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }


    public void saveUserOnDatabase(Usuario user){
        String uuid = mAuth.getUid();
        DocumentReference userRef = db.collection("users").document(uuid);

        userDB.put("nome",user.getNome());
        userDB.put("apelido",user.getApelido());
        userDB.put("dt_nascimento",user.getDt_nascimento());
        userDB.put("curso",user.getCurso());
        userDB.put("genero",user.getGenero());
        userDB.put("ano_ingresso",user.getAno_ingresso());
        userDB.put("biografia",user.getBiografia());
        userDB.put("moradias_anteriores",user.getMoradias_anteriores());
        userDB.put("cidade_natal",user.getCidade_natal());

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
}
