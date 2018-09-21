package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.controller.PessoaControllerImpl;
import br.com.sonner.estagio.dao.api.FuncionarioDAO;
import br.com.sonner.estagio.dao.queries.QueryStringFuncionario;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO {
    private Session session;
    public static FuncionarioDAOImpl FUNCIONARIO_DAO;

    public FuncionarioDAOImpl() {
    }

    public static FuncionarioDAOImpl getInstance() {
        if (FUNCIONARIO_DAO == null) {
            FUNCIONARIO_DAO = new FuncionarioDAOImpl();
        }
        return FUNCIONARIO_DAO;
    }

    @Override
    public void save(Funcionario funcionario) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(funcionario);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Funcionario funcionario) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(funcionario);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Funcionario> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Funcionario> funcionarios = this.session.createQuery("select b from Funcionario as b").list();
            this.session.getTransaction().commit();

            return funcionarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Funcionario funcionario = getOne(id);

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(funcionario);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! funcionario possui relacionamentos");
        } finally {
            this.session.close();
        }

    }

    @Override
    public Funcionario getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Funcionario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Funcionario> pesquisaFuncionario(Pessoa pessoa, String admissao, Long escola) {
        try {
            QueryStringFuncionario queryString = new QueryStringFuncionario.Builder().nome(pessoa.getNome()).admissao(admissao).build();
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
    public List<Funcionario> pesquisaFuncionarioLike(Pessoa pessoa, String admissao, Long escola) {
        try {
            QueryStringFuncionario queryString = new QueryStringFuncionario.Builder().nomeLike(pessoa.getNome()).admissao(admissao).build();
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
