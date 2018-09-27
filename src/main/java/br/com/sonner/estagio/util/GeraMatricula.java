package br.com.sonner.estagio.util;

import java.util.Calendar;

public class GeraMatricula {
    private String numeros;
    private String id;

    public GeraMatricula(String id) {
        this.numeros =  Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        this.id = id;
    }

    public String getMatricula() {
        return this.numeros + this.id;
    }
}
