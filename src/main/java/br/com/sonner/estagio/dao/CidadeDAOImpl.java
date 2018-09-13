package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.CidadeDAO;
import br.com.sonner.estagio.dao.queries.QueryStringCidade;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.CidadeFiltroVO;
import org.hibernate.Session;

import java.util.List;

public class CidadeDAOImpl implements CidadeDAO {
    private static CidadeDAO CIDADE_DAO;
    private Session hibernateConnection;

    private CidadeDAOImpl() {
    }

    public static CidadeDAO getInstance() {
        if (CIDADE_DAO == null) {
            CIDADE_DAO = new CidadeDAOImpl();
        }

        return CIDADE_DAO;
    }


    @Override
    public void save(Cidade cidade) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.save(cidade);
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public List<Cidade> getAll() {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.createQuery("from Cidade").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public Cidade getOne(long id) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.get(Cidade.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public void update(Cidade cidade) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.update(cidade);
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public void delete(long id) throws CustomException {
        try {
            Cidade cidade = getOne(id);
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.remove(cidade);
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossivel deletar!, Cidade possue relacionamento");
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public List<Cidade> pesquisaCidade(CidadeFiltroVO vo) {
        try {
            QueryStringCidade queryString = new QueryStringCidade.Builder().cidade(vo.getNome()).cep(vo.getCep())
                    .codigo(vo.getCod()).estado(vo.getEstado()).build();
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.createQuery(queryString.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public List<Cidade> pesquisaCidadeLike(CidadeFiltroVO vo) {
        try {
            QueryStringCidade queryString = new QueryStringCidade.Builder().cidadeLike(vo.getNome()).cepLike(vo.getCep())
                    .codigoLike(vo.getCod()).estado(vo.getEstado()).build();
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.createQuery(queryString.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }
}
