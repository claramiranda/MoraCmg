package br.unicamp.ft.c155041.moracmg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
    }


    public void onClickCadastrar(View view){
        Toast.makeText(getApplicationContext(),"Logged in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
        Log.d("LOGIN ACTIVITY", "Chama activity cadastro");
    }

    public void onClickLogin(View view) {
        Toast.makeText(getApplicationContext(),"Logged in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.d("LOGIN ACTIVITY", "User login");

    }


}
