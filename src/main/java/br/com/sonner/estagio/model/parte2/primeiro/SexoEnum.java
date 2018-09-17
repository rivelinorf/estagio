package br.com.sonner.estagio.model.parte2.primeiro;

public enum SexoEnum {
    FEMININO("feminino"),
    MASCULINO("masculino");


    private String sexo;

    SexoEnum(String sexo) {
        this.sexo = sexo;
    }


    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}