package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.ProfessorDAO;
import br.com.sonner.estagio.model.parte2.primeiro.Professor;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {
    private Session session;
    public static ProfessorDAOImpl PROFESSOR_DAO;

    public ProfessorDAOImpl() {
    }

    public static ProfessorDAOImpl getInstance() {
        if (PROFESSOR_DAO == null) {
            PROFESSOR_DAO = new ProfessorDAOImpl();
        }
        return PROFESSOR_DAO;
    }

    @Override
    public void save(Professor professor) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(professor);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Professor professor) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(professor);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Professor> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Professor> professors = this.session.createQuery("select b from Professor as b").list();
            this.session.getTransaction().commit();

            return professors;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Professor professor = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(professor);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! professor possui relacionamentos");
        } finally {
            this.session.close();
        }

    }

    @Override
    public Professor getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Professor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }
}
