package br.com.sonner.estagio.servlet.endereco;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sonner.estagio.controller.BairroControllerImpl;
import br.com.sonner.estagio.controller.CidadeControllerImpl;
import br.com.sonner.estagio.controller.EnderecoControllerImpl;
import br.com.sonner.estagio.controller.LogradouroControllerImpl;
import br.com.sonner.estagio.controller.TipoLogradouroControllerImpl;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;

/**
 * Servlet implementation class Insere
 */
@WebServlet("/insere-endereco")
public class Insere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		CidadeFiltroVO cidadevo = new CidadeFiltroVO();

		BairroControllerImpl bairroController = new BairroControllerImpl();
		BairroFiltroVO bairrovo = new BairroFiltroVO();

		HttpSession session = request.getSession();

		cidadevo.setCep("");
		cidadevo.setEstado(null);
		cidadevo.setId(null);
		cidadevo.setNome("");
		cidadevo.setSigla("");

		bairrovo.setCidade(null);
		bairrovo.setId(null);
		bairrovo.setNome("");

		if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
			cidadevo.setEstado(Long.valueOf(request.getParameter("estado")));

			session.setAttribute("filtroCidade_insereEndereco", cidadevo);
			session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
			
		}

		if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
			Long id = Long.valueOf(request.getParameter("cidade"));
			Cidade cidade = cidadeController.getOne(id);

			bairrovo.setCidade(cidade.getId());

			cidadevo.setEstado(cidade.getEstado().getId());

			session.setAttribute("filtroCidade_insereEndereco", cidadevo);
			session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));

			session.setAttribute("filtroBairro_insereEndereco", bairrovo);
			session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));

		}

		if (cidadevo.getEstado() == null) {

			session.setAttribute("listaCidade_insereEndereco", null);
			session.setAttribute("filtroCidade_insereEndereco", null);
			

			if (bairrovo.getCidade() == null) {

				session.setAttribute("listaBairro_insereEndereco", null);
				session.setAttribute("filtroBairro_insereEndereco",null);
			}

		}

		response.sendRedirect("/views/endereco/insere.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
		BairroControllerImpl bairroController = new BairroControllerImpl();
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();

		EnderecoFiltroVO vo = new EnderecoFiltroVO();
		HttpSession session = req.getSession();
		BairroFiltroVO bairrovo = new BairroFiltroVO();
		CidadeFiltroVO cidadevo = new CidadeFiltroVO();

		String cep = req.getParameter("cep");
		String complemento = req.getParameter("complemento");
		Integer numero = null;
		Logradouro logradouro = null;
		Bairro bairro = null;
		TipoLogradouro tipoLogradouro = null;
		Cidade cidade = null;

		if (req.getParameter("numero") != "") {
			numero = Integer.parseInt(req.getParameter("numero"));
		}

		if (req.getParameter("bairro") != "") {
			bairro = bairroController.getOne(Long.valueOf(req.getParameter("bairro")));
			cidade = cidadeController.getOne(bairro.getCidade().getId());
		}

		if (req.getParameter("tipologradouro") != "") {
			tipoLogradouro = tipoLogradouroController.getOne(Long.valueOf(req.getParameter("tipologradouro")));

		}

		if (req.getParameter("logradouro") != "") {
			String nomeLogradouro = req.getParameter("logradouro");
			logradouro = logradouroController.getByNome(nomeLogradouro, cidade, tipoLogradouro);

			if (logradouro == null) {
				logradouro = new Logradouro();

				logradouro.setCidade(cidade);
				logradouro.setNome(nomeLogradouro);
				logradouro.setTipologradouro(tipoLogradouro);

				logradouroController.save(logradouro);
				logradouro = logradouroController.getByNome(nomeLogradouro, cidade, tipoLogradouro);

			}
		}

		Endereco endereco = new Endereco();
		endereco.setNumero(numero);
		endereco.setCep(cep);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setLogradouro(logradouro);

		List<String> erros = enderecoController.validation(endereco);

		if (erros.size() == 0) {

			enderecoController.save(endereco);

			vo.setId(null);
			vo.setNumero(null);
			vo.setCep("");
			vo.setComplemento("");
			vo.setBairro(null);
			vo.setLogradouro(null);

			bairrovo.setCidade(null);
			bairrovo.setNome("");
			bairrovo.setId(null);

			cidadevo.setEstado(null);
			cidadevo.setNome("");
			cidadevo.setSigla("");
			cidadevo.setCep("");
			cidadevo.setId(null);

			session.setAttribute("listaEndereco", enderecoController.filtrar(vo));
			session.setAttribute("filtroCidade_insereEndereco", cidadevo);
			session.setAttribute("listaCidade_insereEndereco", cidadeController.filtrar(cidadevo));
			session.setAttribute("filtroBairro_insereEndereco", bairrovo);
			session.setAttribute("listaBairro_insereEndereco", bairroController.filtrar(bairrovo));
			session.setAttribute("success", "Endereço inserido com sucesso");

			res.sendRedirect("/views/endereco/lista.jsp");
		} else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/insere.jsp");
			requestDispatcher.forward(req, res);
		}

	}

}
