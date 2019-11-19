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



    /*Métodos do Ciclo de Vida*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edTxtEmailLogin);
        edtSenha = findViewById(R.id.edTxtSenha);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /* Métodos Firebase */
    public void  updateUI(FirebaseUser account){
        if(account != null){
            printLog("updateUI:success uid:" + account.getUid());
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            //printToast("Usuário não cadastrado.");
            printLog("updateUI:failure");
        }
    }

    public void signIn(String email, String password){

        if (email != null && password != null){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                printLog("signInWithEmail:success");

                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                printLog("signInWithEmail:failure" +  task.getException());
                                printToast("Authentication failed.");
                                updateUI(null);
                            }

                            // ...
                        }
                    });

        }


    }

    /* Métodos da Classe */
    public void onClickCadastrar(View view){
        printLog("onClick:onClickCadastrar");
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickLogin(View view) {
        printLog("onClick:onClickLogin");

        String email = edtEmail.getText().toString();
        String password = edtSenha.getText().toString();

        if(email == null){
            printToast("E-mail não preenchido.");
        }
        if(password == null){
            printToast("Senha não preenchida.");
        }
        else{
            signIn(email,password);
        }
    }

    public void printToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show();
    }

    public void printLog(String msg){
        Log.d(TAG, msg);
    }


}
