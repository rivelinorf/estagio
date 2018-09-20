package br.com.sonner.estagio.servlet.aluno;

import br.com.sonner.estagio.controller.MatriculaControllerImpl;
import br.com.sonner.estagio.model.Matricula;
import br.com.sonner.estagio.vos.MatriculaFiltroVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet("/matricula-aluno")
class GeraMatricula extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MatriculaControllerImpl matriculaController = new MatriculaControllerImpl();
        MatriculaFiltroVO vo = new MatriculaFiltroVO();
        HttpSession session = request.getSession();


        int numero = gerarNumeroMatricula();
        Date data = gerarDataMatricula();

        Matricula matricula = new Matricula();
        matricula.setNumero(numero);
        matricula.setData(data);

        //so é necessario tratar o numero , sendo assim meu filtrar é criado em relaçao a isso

        List<Matricula> verifica = matriculaController.filtrar(matricula);

        while (verifica.size() != 0) {
            List<Matricula> verifica2 = matriculaController.filtrar(matricula);

            int outronumero = gerarNumeroMatricula();
            matricula.setNumero(outronumero);
            if (verifica2.size() == 0) {
                matriculaController.save(matricula);
            }
        }

        session.setAttribute("matricula", matricula);
    }

}





