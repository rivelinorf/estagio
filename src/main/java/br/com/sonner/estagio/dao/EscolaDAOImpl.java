package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.EscolaDAO;
import br.com.sonner.estagio.dao.queries.QueryStringEscola;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.model.Cidade;
import br.com.sonner.estagio.model.Escola;
import br.com.sonner.estagio.model.Estado;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.EscolaFiltroVO;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
            Query query = this.session.createQuery("select e from Escola as e");
            List<Escola> escolas = query.list();
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

    @Override
    public List<Escola> pesquisaEscola(EscolaFiltroVO vo) {
        try {
            QueryStringEscola queryString = new QueryStringEscola.Builder().bairro(vo.getNome()).endereco(vo.getEndereco())
                    .build();
            this.session = HibernateUtil.getSessionFactory().openSession();
            List<Escola> escolas = this.session.createQuery(queryString.getSql()).list();


            return escolas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Escola> pesquisaEscolaLike(EscolaFiltroVO vo) {
        try {
            QueryStringEscola queryString = new QueryStringEscola.Builder().bairroLike(vo.getNome()).endereco(vo.getEndereco())
                    .build();
            this.session = HibernateUtil.getSessionFactory().openSession();
            List<Escola> escolas = this.session.createQuery(queryString.getSql()).list();

            return escolas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Escola> pesquisaEscolaPorEstado(Estado estado) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            Query query = this.session.createQuery("select e from Escola as e inner join Endereco on e.endereco=Endereco");
            List<Escola> escolas = query.list();
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
    public List<Escola> pesquisaEscolaPorCidade(Cidade cidade) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            Query query = this.session.createQuery("select e from Escola as e " +
                    "inner join Endereco on e.endereco=Endereco " +
                    "inner join Bairro on Endereco .bairro = Bairro " +
                    "inner join Cidade on Bairro .cidade= :cidade");
            query.setParameter("cidade", cidade);
            List<Escola> escolas = query.list();
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
    public List<Escola> pesquisaEscolaPorBairro(Bairro bairro) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            Query query = this.session.createQuery("select e from Escola as e " +
                    "inner join Endereco on e.endereco=Endereco " +
                    "inner join Bairro on Endereco .bairro = :bairro");
            query.setParameter("bairro", bairro);
            List<Escola> escolas = query.list();
            this.session.getTransaction().commit();

            return escolas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }


}
