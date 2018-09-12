package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.connection.Conn;
import br.com.sonner.estagio.dao.api.EnderecoDAO;
import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringEndereco;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAOImpl implements EnderecoDAO {
    private Session session;
    public static EnderecoDAOImpl ENDERECO_DAO;

    public EnderecoDAOImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static EnderecoDAOImpl getInstance() {
        if (ENDERECO_DAO == null) {
            ENDERECO_DAO = new EnderecoDAOImpl();
        }
        return ENDERECO_DAO;
    }

    @Override
    public void save(Endereco endereco) {
        try {
            session.beginTransaction();
            session.save(endereco);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<Endereco> getAll() {
        try {
            session.getTransaction().begin();
            List<Endereco> enderecos = session.createQuery("select e from Endereco as e").list();
            session.getTransaction().commit();

            return enderecos;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }

    }

    @Override
    public void update(Endereco endereco) {
        try {
            session.beginTransaction();
            session.update(endereco);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Long id) {
        Endereco endereco = getOne(id);
        try {
            session.getTransaction().begin();
            session.remove(endereco);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

    }

    @Override
    public Endereco getOne(Long id) {
        return session.find(Endereco.class, id);
    }

    @Override
    public List<Endereco> pesquisaEndereco(EnderecoFiltroVO vo) {
        try {
            QueryStringEndereco queryString = new QueryStringEndereco.Builder()
                    .numero(vo.getNumero())
                    .cep(vo.getCep())
                    .complemento(vo.getComplemento())
                    .bairro(vo.getBairro())
                    .logradouro(vo.getLogradouro())
                    .build();

            session.getTransaction().begin();
            List<Endereco> enderecos = session.createQuery(queryString.getSql()).list();
            session.getTransaction().commit();

            return enderecos;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }

    }

    @Override
    public List<Endereco> pesquisaEnderecoLike(EnderecoFiltroVO vo) {
        try {
            QueryStringEndereco queryString = new QueryStringEndereco.Builder()
                    .numeroLike(vo.getNumero())
                    .cepLike(vo.getCep())
                    .complementoLike(vo.getComplemento())
                    .bairro(vo.getBairro())
                    .logradouro(vo.getLogradouro())
                    .build();

            session.getTransaction().begin();
            List<Endereco> enderecos = session.createQuery(queryString.getSql()).list();
            session.getTransaction().commit();

            return enderecos;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }

    }

}
