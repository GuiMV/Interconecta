package controller;

public class Risco{
    private int Codigo;
    private String Nome;
    private String Descricao;

    public Risco(int n_codigo, String n_nome, String n_descricao){
        this.Codigo = n_codigo;
        this.Nome = n_nome;
        this.Descricao = n_descricao;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int n_codigo) {
        this.Codigo = n_codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String n_nome) {
        this.Nome = n_nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String n_descricao) {
        this.Descricao = n_descricao;
    }

    public boolean equals(Object obj){
        if (obj instanceof Risco){
            Risco r = (Risco) obj;
            if (getCodigo() == r.getCodigo())
                return true;
        }
        return false;
    }

    public String toString(){
        return "Risco{"+
               "codigo = "+this.Codigo+
               ", nome = '"+this.Nome+"'"+
               ", descrição = '"+this.Descricao+"'"+
               "}"; 
    }
}
