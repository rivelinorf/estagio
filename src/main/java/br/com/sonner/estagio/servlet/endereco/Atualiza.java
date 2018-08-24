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
 * Servlet implementation class Atualiza
 */
@WebServlet("/atualiza-endereco")
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		CidadeFiltroVO cidadevo = new CidadeFiltroVO();

		BairroControllerImpl bairroController = new BairroControllerImpl();
		BairroFiltroVO bairrovo = new BairroFiltroVO();

		EnderecoFiltroVO enderecovo = new EnderecoFiltroVO();

		LogradouroFiltroVO logradourovo = new LogradouroFiltroVO();

		cidadevo.setEstado(null);
		cidadevo.setNome("");
		cidadevo.setCod("");
		cidadevo.setCep("");
		cidadevo.setId(null);

		enderecovo.setBairro(null);
		enderecovo.setCep("");
		enderecovo.setComplemento("");
		enderecovo.setNumero(null);
		enderecovo.setLogradouro(null);
		enderecovo.setId(null);

		bairrovo.setCidade(null);
		bairrovo.setNome("");
		bairrovo.setId(null);

		logradourovo.setNome("");
		logradourovo.setCidade(null);
		logradourovo.setTipologradouro(null);

		HttpSession session = request.getSession();

		if (request.getParameter("estado") != "" && request.getParameter("estado") != null) {
			cidadevo.setEstado(Long.valueOf(request.getParameter("estado")));

			session.setAttribute("filtroCidade_atualizaEndereco", cidadevo);
			session.setAttribute("listaCidade_atualizaEndereco", cidadeController.filtrar(cidadevo));

		}

		if (request.getParameter("cidade") != "" && request.getParameter("cidade") != null) {
			Long id = Long.valueOf(request.getParameter("cidade"));
			Cidade cidade = cidadeController.getOne(id);

			bairrovo.setCidade(cidade.getId());

			cidadevo.setEstado(cidade.getEstado().getId());

			enderecovo.setBairro(bairrovo.getId());

			session.setAttribute("filtroCidade_atualizaEndereco", cidadevo);
			session.setAttribute("listaCidade_atualizaEndereco", cidadeController.filtrar(cidadevo));
			session.setAttribute("filtroBairro_atualizaEndereco", bairrovo);
			session.setAttribute("listaBairro_atualizaEndereco", bairroController.filtrar(bairrovo));
			session.setAttribute("filtroLogradouro_atualizaEndereco", logradourovo);

		}

		response.sendRedirect("/views/endereco/atualiza.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LogradouroControllerImpl logradouroController = new LogradouroControllerImpl();
		BairroControllerImpl bairroController = new BairroControllerImpl();
		EnderecoControllerImpl enderecoController = new EnderecoControllerImpl();
		CidadeControllerImpl cidadeController = new CidadeControllerImpl();
		TipoLogradouroControllerImpl tipoLogradouroController = new TipoLogradouroControllerImpl();

		EnderecoFiltroVO vo = new EnderecoFiltroVO();
		HttpSession session = req.getSession();

		String cep = req.getParameter("cep");
		String complemento = req.getParameter("complemento");
		Integer numero = null;
		Logradouro logradouro = null;
		Bairro bairro = null;
		TipoLogradouro tipoLogradouro = null;
		Cidade cidade = null;

		if (req.getParameter("numero") != "" && req.getParameter("numero") != null) {
			numero = Integer.parseInt(req.getParameter("numero"));
		}

		if (req.getParameter("bairro") != "" && req.getParameter("bairro") != null) {
			bairro = bairroController.getOne(Long.valueOf(req.getParameter("bairro")));
			cidade = cidadeController.getOne(bairro.getCidade().getId());
		}

		if (req.getParameter("tipologradouro") != "" && req.getParameter("tipologradouro") != null) {
			tipoLogradouro = tipoLogradouroController.getOne(Long.valueOf(req.getParameter("tipologradouro")));

		}

		if (req.getParameter("logradouro") != "" && req.getParameter("logradouro") != null && cidade != null
				&& tipoLogradouro != null) {
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
		endereco.setId(Long.valueOf(req.getParameter("id")));
		endereco.setNumero(numero);
		endereco.setCep(cep);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setLogradouro(logradouro);

		List<String> erros = enderecoController.validation(endereco);

		if (erros.size() == 0) {

			vo.setId(null);
			vo.setNumero(null);
			vo.setCep("");
			vo.setComplemento("");
			vo.setBairro(null);
			vo.setLogradouro(null);

			vo.setNumero(endereco.getNumero());
			vo.setCep(endereco.getCep());
			vo.setComplemento(endereco.getComplemento());
			vo.setBairro(endereco.getBairro().getId());
			vo.setLogradouro(endereco.getLogradouro().getId());

			List<Endereco> verifica = enderecoController.filtrar(vo);

			if (verifica.size() == 0) {

				enderecoController.update(endereco);

				vo.setNumero(null);
				vo.setCep("");
				vo.setComplemento("");
				vo.setBairro(null);
				vo.setLogradouro(null);

				session.setAttribute("listaEndereco", enderecoController.filtrar(vo));
				session.setAttribute("success", "Endereço atualizado com sucesso");

				res.sendRedirect("/views/endereco/lista.jsp");

			}

			else {

				EnderecoFiltroVO enderecoantigo = (EnderecoFiltroVO) session.getAttribute("enderecoParaEditar");

				if (vo.getBairro().equals(enderecoantigo.getBairro()) && vo.getCep().equals(enderecoantigo.getCep())
						&& vo.getComplemento().equals(enderecoantigo.getComplemento())
						&& vo.getLogradouro().equals(enderecoantigo.getLogradouro())
						&& vo.getBairro().equals(enderecoantigo.getBairro())) {

					vo.setNumero(null);
					vo.setCep("");
					vo.setComplemento("");
					vo.setBairro(null);
					vo.setLogradouro(null);

					session.setAttribute("listaEndereco", enderecoController.filtrar(vo));

					res.sendRedirect("/views/endereco/lista.jsp");

				} else {

					String existe = "Endereço já cadastrado!";

					session.setAttribute("errors", existe);
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/atualiza.jsp");
					requestDispatcher.forward(req, res);
				}
			}
		}

		else {
			session.setAttribute("errors", erros);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/endereco/atualiza.jsp");
			requestDispatcher.forward(req, res);
		}

	}

}