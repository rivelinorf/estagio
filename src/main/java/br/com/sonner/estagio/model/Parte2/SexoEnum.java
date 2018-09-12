package br.com.sonner.estagio.model.Parte2;

public enum SexoEnum {
    FEMININO("feminino"),
    MASCULINO("masculino");


    private String sexualidade;

    SexoEnum(String sexualidade) {
        this.sexualidade = sexualidade;
    }


    public String getSexualidade() {
        return sexualidade;
    }

    public void setSexualidade(String sexualidade) {
        this.sexualidade = sexualidade;
    }
}
