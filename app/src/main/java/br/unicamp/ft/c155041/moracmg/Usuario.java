package br.unicamp.ft.c155041.moracmg;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private final static String TAG = "UsuarioClass";

    private String nome;
    private String email;
    private String ra;
    private String curso;
    private String senha;


    /* Construtores */
    //Todo construtor completo

    public Usuario(String nome, String email, String senha, String curso) {

        if (this.validaEmailDAC(email)){
            Log.d(TAG, "validaEmailDAC:successfull");
            this.email = email;
        }
        else{
            Log.d(TAG, "validaEmailDAC:failure");
            this.email = null;
        }

        this.nome = nome;
        this.curso = curso;
        this.senha = senha;
    }

    /*Metodos de validação da classe*/
    //TODO método de cálculo/validação do RA do usuário através do email

    boolean validaEmailDAC(String email){
        boolean isValid = false;

        if (email.contains("@dac.unicamp.br")){
            isValid = true;
        }
        return isValid;
    }


    /*GETTERS AND SETTERS*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
