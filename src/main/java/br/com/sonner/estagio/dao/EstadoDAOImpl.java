package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.EstadoDAO;
import br.com.sonner.estagio.dao.queries.QueryStringEstado;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.EmptyStackException;
import java.util.List;

public class EstadoDAOImpl implements EstadoDAO {
    private static EstadoDAO ESTADO_DAO;
    private Session hibernateConnection;

    private EstadoDAOImpl() {
    }

    public static EstadoDAO getInstance() {
        if (ESTADO_DAO == null) {
            ESTADO_DAO = new EstadoDAOImpl();
        }

        return ESTADO_DAO;
    }

    @Override
    public void save(Estado estado) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.save(estado);
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public List<Estado> getAll() {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.createQuery("from Estado").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public Estado getOne(long id) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.get(Estado.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public void update(Estado estado) {
        try {
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.update(estado);
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
            Estado estado = getOne(id);
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            this.hibernateConnection.getTransaction().begin();
            this.hibernateConnection.remove(estado);
            this.hibernateConnection.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossivel deletar!, Estado possue relacionamento");
        } finally {
            this.hibernateConnection.close();
        }
    }

    @Override
    public List<Estado> pesquisaEstado(String nome, String abv) {
        try {
            QueryStringEstado queryString = new QueryStringEstado.Builder().estado(nome).abv(abv).build();
            this.hibernateConnection = HibernateUtil.getSessionFactory().openSession();
            return this.hibernateConnection.createQuery(queryString.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.hibernateConnection.close();
        }
    }

    public List<Estado> pesquisaEstadoLike(String nome, String abv) {
        try {
            QueryStringEstado queryString = new QueryStringEstado.Builder().estadoLike(nome).abvLike(abv).build();
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