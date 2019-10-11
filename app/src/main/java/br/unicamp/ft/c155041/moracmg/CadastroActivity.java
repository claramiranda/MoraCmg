package br.unicamp.ft.c155041.moracmg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    private EditText edTxtNome;
    private EditText edTxtEmail;
    private EditText edTxtSenha;
    private Button btnCadastrar;
    private Usuario user;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastro);

        edTxtNome = findViewById(R.id.edTxtNomeCadastro);
        edTxtEmail = findViewById(R.id.edTxtEmailCadastro);
        edTxtSenha = findViewById(R.id.edTxtSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);

        builder = new AlertDialog.Builder(this);

    }

    public void onClickCadastrarUsuario(View view){


        this.user = new Usuario(edTxtNome.getText().toString(), edTxtEmail.getText().toString(), edTxtSenha.getText().toString());

        if (this.user.validaEmailDAC(edTxtEmail.getText().toString())){
            Toast.makeText(getApplicationContext(),"Email DAC ok " + edTxtEmail.getText().toString()  , Toast.LENGTH_SHORT).show();
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


}
