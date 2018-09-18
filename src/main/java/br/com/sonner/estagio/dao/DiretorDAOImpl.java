package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.DiretorDAO;
import br.com.sonner.estagio.model.parte2.primeiro.Diretor;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DiretorDAOImpl implements DiretorDAO {
    private Session session;
    public static DiretorDAOImpl DIRETOR_DAO;

    public DiretorDAOImpl() {
    }

    public static DiretorDAOImpl getInstance() {
        if (DIRETOR_DAO == null) {
            DIRETOR_DAO = new DiretorDAOImpl();
        }
        return DIRETOR_DAO;
    }

    @Override
    public void save(Diretor aluno) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(aluno);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Diretor aluno) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(aluno);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Diretor> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Diretor> alunos = this.session.createQuery("select b from Diretor as b").list();
            this.session.getTransaction().commit();

            return alunos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Diretor aluno = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(aluno);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! diretor possui relacionamentos");
        } finally {
            this.session.close();
        }

    }

    @Override
    public Diretor getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Diretor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }
}
