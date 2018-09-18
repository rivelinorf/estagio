package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.EscolaDAO;
import br.com.sonner.estagio.model.parte2.segundo.Escola;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EscolaDAOImpl implements EscolaDAO {
    private Session session;
    private static EscolaDAOImpl ESCOLA_DAO;

    public EscolaDAOImpl() {
    }

    public static EscolaDAOImpl getInstance() {
        if (ESCOLA_DAO == null) {
            ESCOLA_DAO = new EscolaDAOImpl();
        }
        return ESCOLA_DAO;
    }


    @Override
    public void save(Escola escola) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(escola);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Escola> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Escola> escolas = this.session.createQuery("select e from Escola as e").list();
            this.session.getTransaction().commit();

            return escolas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Escola escola) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(escola);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Escola escola = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(escola);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! EscolaController possui relacionamentos");
        } finally {
            this.session.close();
        }
    }

    @Override
    public Escola getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Escola.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }


    }
}
