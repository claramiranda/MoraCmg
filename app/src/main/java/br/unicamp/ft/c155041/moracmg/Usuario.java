package br.unicamp.ft.c155041.moracmg;

import android.content.Context;
import android.widget.Toast;

public class Usuario {
    private String nome;
    private String email;
    private String ra;
    private String curso;
    private String senha;



    public Usuario(String nome, String email, String curso) {
        this.nome = nome;
        if (this.validaEmailDAC(email)){
            this.email = email;
        }
        this.curso = curso;
    }

    /*Metodos da classe*/

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
