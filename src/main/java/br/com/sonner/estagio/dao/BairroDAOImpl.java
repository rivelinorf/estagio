package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.BairroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringBairro;
import br.com.sonner.estagio.model.Bairro;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.BairroFiltroVO;
import org.hibernate.Session;

import java.util.List;

public class BairroDAOImpl implements BairroDAO {
    private Session session;
    public static BairroDAOImpl BAIRRO_DAO;

    public BairroDAOImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static BairroDAOImpl getInstance() {
        if (BAIRRO_DAO == null) {
            BAIRRO_DAO = new BairroDAOImpl();
        }
        return BAIRRO_DAO;
    }

    @Override
    public void save(Bairro bairro) {
        try {
            session.beginTransaction();
            session.save(bairro);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Bairro bairro) {
        try {
            session.beginTransaction();
            session.update(bairro);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Bairro> getAll() {
        try {
            session.getTransaction().begin();
            List<Bairro> bairros = session.createQuery("select b from br.com.sonner.estagio.model.Bairro as b").list();
            session.getTransaction().commit();

            return bairros;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Bairro bairro = getOne(id);
        try {
            this.session.getTransaction().begin();
            this.session.remove(bairro);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! Bairro possui relacionamentos");
        } finally {
            this.session.close();
        }

    }

    @Override
    public Bairro getOne(Long id) {
        try {
            return this.session.find(Bairro.class, id);
        } catch (Exception e) {
            this.session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
        }


    }

    @Override
    public List<Bairro> pesquisaBairro(BairroFiltroVO vo) {
        try {
            QueryStringBairro queryString = new QueryStringBairro.Builder().bairro(vo.getNome()).cidade(vo.getCidade())
                    .build();

            this.session.getTransaction().begin();
            List<Bairro> bairros = this.session.createQuery(queryString.getSql()).list();
            this.session.getTransaction().commit();

            return bairros;
        } catch (Exception e) {
            this.session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Bairro> pesquisaBairroLike(BairroFiltroVO vo) {
        try {
            QueryStringBairro queryString = new QueryStringBairro.Builder().bairroLike(vo.getNome()).cidade(vo.getCidade())
                    .build();

            this.session.getTransaction().begin();
            List<Bairro> bairros = this.session.createQuery(queryString.getSql()).list();
            this.session.getTransaction().commit();

            return bairros;
        } catch (Exception e) {
            this.session.getTransaction().rollback();
            return null;
        } finally {
            this.session.close();
        }
    }

}
