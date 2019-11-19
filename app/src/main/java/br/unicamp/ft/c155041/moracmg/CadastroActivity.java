package br.unicamp.ft.c155041.moracmg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {
    private EditText edTxtNome;
    private EditText edTxtEmail;
    private EditText edTxtSenha;
    private Button btnCadastrar;
    private Usuario user;
    private AlertDialog.Builder builder;

    private FirebaseAuth mAuth;
    public final static String TAG = "CadastroActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastro);

        edTxtNome = findViewById(R.id.edTxtNomeCadastro);
        edTxtEmail = findViewById(R.id.edTxtEmailCadastro);
        edTxtSenha = findViewById(R.id.edTxtSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);

        builder = new AlertDialog.Builder(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    public void onClickCadastrarUsuario(View view){


        this.user = new Usuario(edTxtNome.getText().toString(), edTxtEmail.getText().toString(), edTxtSenha.getText().toString());

        if (this.user.validaEmailDAC(edTxtEmail.getText().toString())){
            //Toast.makeText(getApplicationContext(),"Email DAC ok " + edTxtEmail.getText().toString()  , Toast.LENGTH_SHORT).show();
            signIn();
        }
        else{
            exibirAlertaEmail(view);
        }

    }

    public void selecionarFotoUsuario(View view){
        Toast.makeText(getApplicationContext(),"Selecionar foto do usuario novo", Toast.LENGTH_SHORT).show();

    }

    public void exibirAlertaEmail(View v) {
        //Setting message manually and performing action on button click
        builder.setMessage("Eita! Você inseriu um e-mail inválido. Por hora só é possivel cadastrar-se no aplicativo usando e-mail acadêmico")
                .setCancelable(true)
                .setPositiveButton("Alterar e-mail", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Legal, o modelo é c123456@dac.unicamp.br",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Não possuo um e-mail acadêmico.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Toast.makeText(getApplicationContext(),"Por enquanto esse aplicativo está disponível apenas para alunos da Unicamp. \n Tente novamente daqui a alguns meses.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            AlertDialog alert = builder.create();
            alert.setTitle("Alerta! E-mail inválido!");
            alert.show();
    }

    public void signIn(){
        mAuth.createUserWithEmailAndPassword(this.user.getEmail(), this.user.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //Change UI according to user data.
    //todo implementar updateUI
    public void  updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }


}
