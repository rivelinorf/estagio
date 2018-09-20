package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.MatriculaDAO;
import br.com.sonner.estagio.dao.queries.QueryStringMatricula;
import br.com.sonner.estagio.dao.queries.QueryStringTurma;
import br.com.sonner.estagio.model.Matricula;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class MatriculaDAOImpl implements MatriculaDAO {
    private Session session;
    public static MatriculaDAOImpl MATRICULA_DAO;

    public MatriculaDAOImpl() {
    }

    public static MatriculaDAOImpl getInstance() {
        if (MATRICULA_DAO == null) {
            MATRICULA_DAO = new MatriculaDAOImpl();
        }
        return MATRICULA_DAO;
    }


    @Override
    public void save(Matricula matricula) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(matricula);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Matricula> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Matricula> matriculas = this.session.createQuery("select m from Matricula as m").list();
            this.session.getTransaction().commit();

            return matriculas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Matricula matricula) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(matricula);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void delete(Long id) throws CustomException {
        Matricula matricula = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(matricula);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! Bairro possui relacionamentos");
        } finally {
            this.session.close();
        }
    }

    @Override
    public Matricula getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Matricula.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    public List<Matricula> pesquisarMatricula(Matricula matricula) {
        try {
            QueryStringMatricula queryStringMatricula = new QueryStringMatricula.Builder().matricula(matricula.getNumero()).build();
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.createQuery(queryStringMatricula.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }
}
