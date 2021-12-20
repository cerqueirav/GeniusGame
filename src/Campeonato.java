package src;

public class Campeonato {
    String titulo;
    String data;

    public Campeonato(String titulo, String data){
        setTitulo(titulo);
        setData(data);
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getData(){
        return this.data;
    }
}
