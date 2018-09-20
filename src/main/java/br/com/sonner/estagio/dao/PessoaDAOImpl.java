package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.PessoaDAO;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PessoaDAOImpl implements PessoaDAO {
    private Session session;
    public static PessoaDAOImpl PESSOA_DAO;

    public PessoaDAOImpl() {
    }

    public static PessoaDAOImpl getInstance() {
        if (PESSOA_DAO == null) {
            PESSOA_DAO = new PessoaDAOImpl();
        }
        return PESSOA_DAO;
    }

    @Override
    public void save(Pessoa pessoa) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(pessoa);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Pessoa pessoa) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(pessoa);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Pessoa> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Pessoa> pessoas = this.session.createQuery("select b from Pessoa as b").list();
            this.session.getTransaction().commit();

            return pessoas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Pessoa pessoa = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(pessoa);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! pessoa possui relacionamentos");
        } finally {
            this.session.close();
        }

    }

    @Override
    public Pessoa getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Pessoa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }
}
