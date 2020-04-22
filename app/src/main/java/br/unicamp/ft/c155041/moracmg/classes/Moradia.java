package br.unicamp.ft.c155041.moracmg.classes;

public class Moradia {

    private int idMoradia;
    private String moradiaNome;
    private int qtdMoradores;
    private String genero;
    private String moradiaDesc;
    private double nivelAgitacao;
    private String moradiaReferencia;
    private String moradiaTipo;
    private int moradiaFoto;

    //aqui depois precisa ter um list de usuarios, que sao os moradores/administradores do perfil/contatos
    //private String moradiaContato;

    //Lista das vagas publicadas por aquela moradia
    //public Moradia(int idMoradia, String moradiaNome, int qtdMoradores, String moradiaGenero, String moradiaDesc, double moradiaAgitacao, String moradiaLocalizacao, String moradiaContato, int moradiaFoto, String moradiaTipo) {
    public Moradia(int idMoradia, String moradiaNome, int qtdMoradores, String genero, String moradiaDesc, double nivelAgitacao, String moradiaReferencia, int moradiaFoto, String moradiaTipo) {

        this.idMoradia = idMoradia;
        this.moradiaNome = moradiaNome;
        this.qtdMoradores = qtdMoradores;
        this.genero = genero;
        this.moradiaDesc = moradiaDesc;
        this.nivelAgitacao = nivelAgitacao;
        this.moradiaReferencia = moradiaReferencia;
        //this.moradiaContato = moradiaContato;
        this.moradiaFoto = moradiaFoto;
        this.moradiaTipo = moradiaTipo;
    }

    public String getMoradiaReferencia() {
        return moradiaReferencia;
    }

    public void setMoradiaReferencia(String moradiaReferencia) {
        this.moradiaReferencia = moradiaReferencia;
    }

    public String getMoradiaNome() {
        return moradiaNome;
    }

    public void setMoradiaNome(String moradiaNome) {
        this.moradiaNome = moradiaNome;
    }

    public int getQtdMoradores() {
        return qtdMoradores;
    }

    public void setQtdMoradores(int qtdMoradores) {
        this.qtdMoradores = qtdMoradores;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMoradiaDesc() {
        return moradiaDesc;
    }

    public void setMoradiaDesc(String moradiaDesc) {
        this.moradiaDesc = moradiaDesc;
    }

    public double getNivelAgitacao() {
        return nivelAgitacao;
    }

    public void setNivelAgitacao(double nivelAgitacao) {
        this.nivelAgitacao = nivelAgitacao;
    }

    public int getMoradiaFoto() {
        return moradiaFoto;
    }

    public void setMoradiaFoto(int moradiaFoto) {
        this.moradiaFoto = moradiaFoto;
    }

    public String getMoradiaTipo() {
        return moradiaTipo;
    }

    public void setMoradiaTipo(String moradiaTipo) {
        this.moradiaTipo = moradiaTipo;
    }

    public int getIdMoradia() {
        return idMoradia;
    }

    public void setIdMoradia(int idMoradia) {
        this.idMoradia = idMoradia;
    }
}
