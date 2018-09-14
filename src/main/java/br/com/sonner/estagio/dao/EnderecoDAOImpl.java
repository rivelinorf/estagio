package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.EnderecoDAO;
import br.com.sonner.estagio.dao.queries.QueryStringEndereco;
import br.com.sonner.estagio.model.Endereco;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.EnderecoFiltroVO;
import org.hibernate.Session;

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
            this.session.beginTransaction();
            this.session.save(endereco);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            this.session.getTransaction().rollback();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Endereco> getAll() {
        try {
            this.session.getTransaction().begin();
            List<Endereco> enderecos = this.session.createQuery("select e from Endereco as e").list();
            this.session.getTransaction().commit();

            return enderecos;
        } catch (Exception e) {
            this.session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void update(Endereco endereco) {
        try {
            this.session.beginTransaction();
            this.session.update(endereco);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            this.session.getTransaction().rollback();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Endereco endereco = getOne(id);
        try {
            this.session.getTransaction().begin();
            this.session.remove(endereco);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            this.session.getTransaction().rollback();
        } finally {
            this.session.close();
        }


    }

    @Override
    public Endereco getOne(Long id) {
        try {
            return this.session.find(Endereco.class, id);
        } catch (Exception e) {
            this.session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public List<Endereco> pesquisaEndereco(EnderecoFiltroVO vo) {
        this.session.getTransaction().begin();
        try {
            QueryStringEndereco queryString = new QueryStringEndereco.Builder()
                    .numero(vo.getNumero())
                    .cep(vo.getCep())
                    .complemento(vo.getComplemento())
                    .bairro(vo.getBairro())
                    .logradouro(vo.getLogradouro())
                    .build();

            List<Endereco> enderecos = this.session.createQuery(queryString.getSql()).list();
            this.session.getTransaction().commit();

            return enderecos;
        } catch (Exception e) {
            this.session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
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

            this.session.getTransaction().begin();
            List<Endereco> enderecos = this.session.createQuery(queryString.getSql()).list();
            this.session.getTransaction().commit();

            return enderecos;
        } catch (Exception e) {
            this.session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
        }

    }

}
