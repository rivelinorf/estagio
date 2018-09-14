package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.TipoLogradouroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringTipologradouro;
import br.com.sonner.estagio.model.TipoLogradouro;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.TipologradouroFiltroVO;
import org.hibernate.Session;

import java.util.List;

public class TipoLogradouroDAOImpl implements TipoLogradouroDAO {

    private Session hibernateConnection;
    private static TipoLogradouroDAO TIPOLOGRADOURO_DAO;

    public TipoLogradouroDAOImpl() {
        this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
    }

    public static TipoLogradouroDAO getInstance() {
        if (TIPOLOGRADOURO_DAO == null) {
            TIPOLOGRADOURO_DAO = new TipoLogradouroDAOImpl();
        }
        return TIPOLOGRADOURO_DAO;
    }

    @Override
    public void save(TipoLogradouro tipoLogradouro) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.save(tipoLogradouro);
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public List<TipoLogradouro> getAll() {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.createQuery("from TipoLogradouro ").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }

    }

    @Override
    public TipoLogradouro getOne(Long id) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.get(TipoLogradouro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public void update(TipoLogradouro tipoLogradouro) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.update(tipoLogradouro);
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.remove(getOne(id));
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public List<TipoLogradouro> pesquisaTipoLogradouro(String nome) {
        try {
            QueryStringTipologradouro queryString = new QueryStringTipologradouro.Builder().tipologradouro(nome).build();
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
    public List<TipoLogradouro> pesquisaTipoLogradouroLike(TipologradouroFiltroVO vo) {
        try {
            QueryStringTipologradouro queryString = new QueryStringTipologradouro.Builder().tipologradouroLike(vo.getNome()).build();
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
