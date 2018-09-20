package br.com.sonner.estagio.servlet.matricula;

import br.com.sonner.estagio.model.Matricula;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GeradorMatricula {

    private static Date gerarDataMatricula() {

        Calendar c = Calendar.getInstance();
        Date data = c.getTime();

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        dataFormatada.format(data);

        return data;
    }

    public static int gerarNumeroMatricula() {
        Random rn = new Random();

        int n = 5000 - 1000 + 1;
        int i = rn.nextInt() % n;
        int numeroMatricula = 10 + i;
        while(numeroMatricula<0){
            novo numero
        }

//
//        int numeroMatricula = 0;
//        double numero = Math.random();
//        numeroMatricula = (int) (numero * 100);


        return numeroMatricula;
    }

    public static void main(String[] args) {
        while (true) {
            Matricula matricula = new Matricula();

            int numero = gerarNumeroMatricula();


            Date data = gerarDataMatricula();

            matricula.setNumero(numero);
            matricula.setData(data);


            System.out.println(matricula.getNumero());
            System.out.println(matricula.getData());
        }
    }
}
