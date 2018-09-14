package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringLogradouro;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;
import org.hibernate.Session;

import java.util.List;

public class LogradouroDAOImpl implements LogradouroDAO {

    private Session session;
    private static LogradouroDAO LOGRADOURO_DAO;

    public LogradouroDAOImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static LogradouroDAO getIntance() {
        if (LOGRADOURO_DAO == null) {
            LOGRADOURO_DAO = new LogradouroDAOImpl();
        }
        return LOGRADOURO_DAO;
    }


    @Override
    public void save(Logradouro logradouro) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.save(logradouro);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }


    @Override
    public List<Logradouro> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.createQuery("from Logradouro ").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public Logradouro getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.get(Logradouro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Logradouro logradouro) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.update(logradouro);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        try {
            Logradouro logradouro = getOne(id);
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(logradouro);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossivel deletar!, Logradouro possui relacionamento");
        } finally {
            this.session.close();
        }

    }

    @Override
    public List<Logradouro> pesquisaLogradouro(LogradouroFiltroVO vo) {
        try {
            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder().logradouro(vo.getNome())
                    .tipologradouro(vo.getTipologradouro())
                    .cidade(vo.getCidade())
                    .build();
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.createQuery(queryString.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }


    }

    @Override
    public List<Logradouro> pesquisaLogradouroLike(LogradouroFiltroVO vo) {
        try {
            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder().logradouroLike(vo.getNome())
                    .tipologradouro(vo.getTipologradouro())
                    .cidade(vo.getCidade())
                    .build();
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.createQuery(queryString.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }

    }
}