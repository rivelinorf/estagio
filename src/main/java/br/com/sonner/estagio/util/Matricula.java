package br.com.sonner.estagio.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Matricula {
    private static Date gerarDataMatricula() {

        Calendar c = Calendar.getInstance();
        Date data = c.getTime();

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        dataFormatada.format(data);

        return data;
    }

    public static int gerarNumeroMatricula() {
        Random rn = new Random();

        int n = 500 - 100 + 1;
        int i = rn.nextInt() % n;
        int numeroMatricula = 10 + i;
        while (numeroMatricula < 0) {
            int numero = gerarNumeroMatricula();
            numeroMatricula = numero;
        }
        return numeroMatricula;
    }

    public br.com.sonner.estagio.model.Matricula geraMatricula() {
        br.com.sonner.estagio.model.Matricula matricula = new br.com.sonner.estagio.model.Matricula();
        Date data = gerarDataMatricula();
        int numero = gerarNumeroMatricula();
        matricula.setData(data);
        matricula.setNumero(numero);

        return matricula;
    }


}
