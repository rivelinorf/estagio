package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.DisciplinaDAO;
import br.com.sonner.estagio.model.parte2.segundo.Disciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DisciplinaDAOImpl implements DisciplinaDAO {

    private Session session;
    public static DisciplinaDAOImpl DISCIPLINA_DAO;

    public DisciplinaDAOImpl() {
    }

    public static DisciplinaDAOImpl getInstance() {
        if (DISCIPLINA_DAO == null) {
            DISCIPLINA_DAO = new DisciplinaDAOImpl();
        }
        return DISCIPLINA_DAO;
    }

    @Override
    public void save(Disciplina disciplina) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(disciplina);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Disciplina> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Disciplina> disciplinas = this.session.createQuery("select d from Disciplina as d").list();
            this.session.getTransaction().commit();

            return disciplinas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public Disciplina getOne(long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Disciplina.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Disciplina disciplina) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(disciplina);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void delete(long id) throws CustomException {
        Disciplina disciplina = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(disciplina);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! disciplina  possui relacionamentos");
        } finally {
            this.session.close();
        }
    }
}
