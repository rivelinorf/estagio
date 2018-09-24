package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.DiretorDAO;
import br.com.sonner.estagio.dao.queries.QueryStringFuncionario;
import br.com.sonner.estagio.model.Diretor;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.DiretorFiltroVO;
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
    public void save(Diretor diretor) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(diretor);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Diretor diretor) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(diretor);
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
            List<Diretor> diretors = this.session.createQuery("select b from Diretor as b").list();
            this.session.getTransaction().commit();

            return diretors;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Diretor diretor = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(diretor);
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

    public List<Diretor> pesquisaDiretor(DiretorFiltroVO diretoresPesquisados) {
        try {
            QueryStringFuncionario queryString = new QueryStringFuncionario.Builder()
                    .table("Diretor")
                    .nome(diretoresPesquisados.getFuncionario().getPessoa().getNome())
                    .cpf(diretoresPesquisados.getFuncionario().getPessoa().getCpf())
                    .admissao(diretoresPesquisados.getFuncionario().getAdmissao())
                    .build();

            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.createQuery(queryString.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    public List<Diretor> pesquisaDiretorLike(DiretorFiltroVO diretoresPesquisados) {
        try {
            QueryStringFuncionario queryString = new QueryStringFuncionario.Builder().table("Diretor")
                    .nomeLike(diretoresPesquisados.getFuncionario().getPessoa().getNome())
                    .admissao(diretoresPesquisados.getFuncionario().getAdmissao())
                    .cpf(diretoresPesquisados.getFuncionario().getPessoa().getCpf())
                    .escola(diretoresPesquisados.getFuncionario().getEscola().getId())
                    .build();

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
