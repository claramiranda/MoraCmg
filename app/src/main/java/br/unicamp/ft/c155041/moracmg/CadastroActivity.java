package br.unicamp.ft.c155041.moracmg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    public final static String TAG = "CadastroActivity";

    private EditText edTxtNome;
    private EditText edTxtEmail;
    private EditText edTxtSenha;
            Spinner spinner;
    private Button btnCadastrar;
    private Usuario user;
    private AlertDialog.Builder builder;

    private FirebaseAuth mAuth;


    /* Métodos do Ciclo de Vida */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastro);

        edTxtNome = findViewById(R.id.edTxtNomeCadastro);
        edTxtEmail = findViewById(R.id.edTxtEmailCadastro);
        edTxtSenha = findViewById(R.id.edTxtSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);
        spinner = findViewById(R.id.spinnerCursos);

        //dialog initialization
        builder = new AlertDialog.Builder(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /*Métodos Firebase*/
    public void  updateUI(FirebaseUser account){
        if(account != null){
            printLog("updateUI:successfull");
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }else {
            printLog("updateUI:failure");
            printToast("Erro ao cadastrar usuário.");
        }
    }

    public void createAccount(){
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


    /*Métodos da Classe*/


    //TODO implementar metodo ler foto
    public void selecionarFotoUsuario(View view){
        printLog("onClick:selecionarFotoUsuario");
        printToast("onClick:selecionarFotoUsuario");
    }

    public void onClickCadastrarUsuario(View view){

        printLog("onClick:onClickCadastrarUsuario");

        user = new Usuario(edTxtNome.getText().toString(),
                edTxtEmail.getText().toString(),
                edTxtSenha.getText().toString(),
                spinner.getSelectedItem().toString());

        if (user.getEmail() == null){
            alertaEmailInvalido(view);
        }
        else{
            printUserOnLog(user);
            createAccount();
        }

    }

    public void alertaEmailInvalido(View v) {
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

    public void printUserOnLog(Usuario user){
        Log.d(TAG, "user.nome=" + user.getNome());
        //Log.d(TAG, "user.ra=" + user.getRa());
        Log.d(TAG, "user.email=" + user.getEmail());
        Log.d(TAG, "user.curso=" + user.getCurso());
        Log.d(TAG, "user.senha=" + user.getSenha());
    }

    public void printToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show();
    }

    public void printLog(String msg){
        Log.d(TAG, msg);
    }
}
