package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.TurmaDisciplinaDAO;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TurmaDisciplinaDAOImpl implements TurmaDisciplinaDAO {
    private Session session;
    public static TurmaDisciplinaDAOImpl TURMADISCIPLINA_DAO;

    public TurmaDisciplinaDAOImpl() {
    }

    public static TurmaDisciplinaDAOImpl getInstance() {
        if (TURMADISCIPLINA_DAO == null) {
            TURMADISCIPLINA_DAO = new TurmaDisciplinaDAOImpl();
        }
        return TURMADISCIPLINA_DAO;
    }

    @Override
    public void save(TurmaDisciplina turmaDisciplina) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(turmaDisciplina);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(TurmaDisciplina turmaDisciplina) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(turmaDisciplina);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<TurmaDisciplina> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<TurmaDisciplina> turmaDisciplinas = this.session.createQuery("select b from TurmaDisciplina as b").list();
            this.session.getTransaction().commit();

            return turmaDisciplinas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        TurmaDisciplina turmaDisciplina = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(turmaDisciplina);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! TurmaDisciplinaControllerImpl possui relacionamentos");
        } finally {
            this.session.close();
        }

    }

    @Override
    public TurmaDisciplina getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(TurmaDisciplina.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }
}
