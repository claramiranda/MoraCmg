package br.unicamp.ft.c155041.moracmg;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {

    private FirebaseAuth mAuth;
    String uuid;
    FirebaseFirestore db;
    Map<String, Object> userDB = new HashMap<>();


    public DatabaseHandler() {

        mAuth = FirebaseAuth.getInstance();
        uuid = mAuth.getUid();
        db = FirebaseFirestore.getInstance();

    }


    public void saveUserOnDatabase(Usuario user){


    }
}
