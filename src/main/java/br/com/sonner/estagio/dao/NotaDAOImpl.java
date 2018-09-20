package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.NotaDAO;
import br.com.sonner.estagio.model.Nota;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class NotaDAOImpl implements NotaDAO {
    private Session session;
    public static NotaDAOImpl NOTA_DAO;

    public NotaDAOImpl() {
    }

    public static NotaDAOImpl getInstance() {
        if (NOTA_DAO == null) {
            NOTA_DAO = new NotaDAOImpl();
        }
        return NOTA_DAO;
    }

    @Override
    public void save(Nota nota) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(nota);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Nota> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Nota> notas = this.session.createQuery("select n from Nota as n").list();
            this.session.getTransaction().commit();

            return notas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Nota nota) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(nota);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();

        }
    }

    @Override
    public void delete(Long id) throws CustomException {
        Nota nota = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(nota);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! nota possui relacionamentos");
        } finally {
            this.session.close();
        }
    }

    @Override
    public Nota getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Nota.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }
}
