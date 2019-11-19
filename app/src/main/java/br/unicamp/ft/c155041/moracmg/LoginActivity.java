package br.unicamp.ft.c155041.moracmg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    public final static String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    EditText edtSenha;
    EditText edtEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edTxtEmailLogin);
        edtSenha = findViewById(R.id.edTxtSenha);

    }

    //TODO Implementar login com o firebase
    //todo ler elementos da interface gráfica
    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //Metodos Firebase
    //Change UI according to user data.
    //todo implementar updateUI
    public void  updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }

    public void signIn(){
        String email = edtEmail.getText().toString();
        String password = edtSenha.getText().toString();

        if (email != null && password != null){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });

        }


    }

    //Métodos \on Click

    public void onClickCadastrar(View view){
        Toast.makeText(getApplicationContext(),"Logged in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
        Log.d("LOGIN ACTIVITY", "Chama activity cadastro");
    }

    public void onClickLogin(View view) {


        signIn();
        /*
        Toast.makeText(getApplicationContext(),"Logged in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.d("LOGIN ACTIVITY", "User login");
         */

    }


}
