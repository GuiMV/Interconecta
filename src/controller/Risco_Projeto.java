package controller;

public class Risco_Projeto {
    private int Id;
    private int Cod_Risco;
    private float Probabilidade;
    private float Impacto;

    public Risco_Projeto(int n_Id, int n_Cod_RIsco, float n_Probabilidade, float n_Impacto){
        this.Id = n_Id;
        this.Cod_Risco = n_Cod_RIsco;
        this.Probabilidade = n_Probabilidade;
        this.Impacto = n_Impacto;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int n_id) {
        this.Id = n_id;
    }

    public int getCod_Risco() {
        return this.Cod_Risco;
    }

    public void setCod_Risco(int n_cod_Risco) {
        this.Cod_Risco = n_cod_Risco;
    }

    public float getProbabilidade() {
        return this.Probabilidade;
    }

    public void setProbabilidade(float n_probabilidade) {
        this.Probabilidade = n_probabilidade;
    }

    public float getImpacto() {
        return this.Impacto;
    }

    public void setImpacto(float n_impacto) {
        this.Impacto = n_impacto;
    }
    
    public boolean equals(Object obj){
        if (obj instanceof Risco_Projeto){
            Risco_Projeto RP = (Risco_Projeto) obj;
            if (getId() == RP.getId())
                return true;
        }
        return false;
    }

    public String toString(){
        return "Risco{"+
               "Id = "+this.Id+
               ", Cod_Risco = "+this.Cod_Risco+
               ", Probabilidade = "+this.Probabilidade+
               ", Impacto = "+this.Impacto+
               "}"; 
    }
}