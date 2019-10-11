package br.unicamp.ft.c155041.moracmg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
    }


    public void onClickCadastrar(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }


}
