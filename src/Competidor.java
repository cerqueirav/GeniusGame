package src;

import java.util.Scanner;

public class Competidor implements Comparable<Competidor>{
    private String nome;
    private String apelido;
    private String id;
    private int recordRodada;
    private int pontuacao;

    public Competidor(String nome, String id){
        this.setNome(nome);
        this.setId(id);
        this.pontuacao = 0;
        this.recordRodada = 0;
    }

    public void setRecordRodada(int rodadaMax){
        this.recordRodada = rodadaMax;
    }

    public int getRodadaRecord(){
        return this.recordRodada;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setPontuacao(int pontuacao){
        this.pontuacao = pontuacao;
    }

    public int getPontuacao(){
        return this.pontuacao;
    }

    @Override
    public int compareTo(Competidor competidorP) {
        return (this.pontuacao - competidorP.getPontuacao()); 
    }
}
