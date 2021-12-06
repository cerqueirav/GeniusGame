package src;

public class Usuario{
    private String nome;
    private String id;
    private int recordRodada;
    private int pontuacao;

    public Usuario(String nome, String idPlayer){
        this.setId(idPlayer);
        this.setNome(nome);
        this.pontuacao = 0;
        this.recordRodada = 0;
    }

    public void setRecordRodada(int rodadaMax){
        this.recordRodada = rodadaMax;
    }

    public int getRodadaRecord(){
        return this.recordRodada;
    }

    public void setId(String idPlayer){
        this.id = idPlayer;
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
}
