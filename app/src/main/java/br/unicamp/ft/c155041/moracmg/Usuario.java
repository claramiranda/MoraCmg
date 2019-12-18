package br.unicamp.ft.c155041.moracmg;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private final static String TAG = "UsuarioClass";

    private int foto;
    private String nome;
    private String email;
    private String ra;
    private String curso;
    private String senha;
    private String genero;
    private String dt_nascimento;
    private String ano_ingresso;
    private String apelido;
    private String bio;
    private String moradiasAnteriores;
    private String cidade_natal;

    /* Construtores */

    public Usuario() {
    }

    public Usuario(String email, String senha) {

        if (this.validaEmailDAC(email)){
            Log.d(TAG, "validaEmailDAC:successfull");
            this.email = email;
        }
        else{
            Log.d(TAG, "validaEmailDAC:failure");
            this.email = null;
        }

        this.senha = senha;
    }

    public Usuario(int foto, String nome, String email, String ra, String curso, String senha, String genero,
                   String dt_nascimento, String ano_ingresso, String apelido, String bio, String moradiasAnteriores, String cidade_natal) {
        this.foto = foto;
        this.nome = nome;
        this.email = email;
        this.ra = ra;
        this.curso = curso;
        this.senha = senha;
        this.genero = genero;
        this.dt_nascimento = dt_nascimento;
        this.ano_ingresso = ano_ingresso;
        this.apelido = apelido;
        this.bio = bio;
        this.moradiasAnteriores = moradiasAnteriores;
        this.cidade_natal = cidade_natal;
    }

    public Usuario(String nome, String email, String curso, String genero, String dt_nascimento, String ano_ingresso,
                   String apelido, String bio, String moradiasAnteriores, String cidade_natal) {

        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.genero = genero;
        this.dt_nascimento = dt_nascimento;
        this.ano_ingresso = ano_ingresso;
        this.apelido = apelido;
        this.bio = bio;
        this.moradiasAnteriores = moradiasAnteriores;
        this.cidade_natal = cidade_natal;
        this.ra = calculaRA();
    }

    /*Metodos de validação da classe*/
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
        return this.ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String calculaRA() {

        String email = getEmail();
        String[] output = email.split("@");
        email = output[0];
        email = email.substring(1);
        //this.setRa(email);
        return email;
        //Toast.makeText(this, email,Toast.LENGTH_LONG).show();

/*

        String[] output = email.split("@");
        email = output[0];

        output = email.split("0-9");
        email = output[1];
        return email;
*/

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getAno_ingresso() {
        return ano_ingresso;
    }

    public void setAno_ingresso(String ano_ingresso) {
        this.ano_ingresso = ano_ingresso;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getMoradiasAnteriores() {
        return moradiasAnteriores;
    }

    public void setMoradiasAnteriores(String moradiasAnteriores) {
        this.moradiasAnteriores = moradiasAnteriores;
    }

    public String getCidade_natal() {
        return cidade_natal;
    }

    public void setCidade_natal(String cidade_natal) {
        this.cidade_natal = cidade_natal;
    }
}
