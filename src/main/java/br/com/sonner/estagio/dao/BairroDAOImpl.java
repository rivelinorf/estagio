package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.BairroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringBairro;
import br.com.sonner.estagio.model.Bairro;
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
        }

    }

    @Override
    public void delete(Long id) {
        Bairro bairro = getOne(id);
        try {
            session.getTransaction().begin();
            session.remove(bairro);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

    }

    @Override
    public Bairro getOne(Long id) {
        return session.find(Bairro.class, id);

    }

    @Override
    public List<Bairro> pesquisaBairro(BairroFiltroVO vo) {
        try {
            QueryStringBairro queryString = new QueryStringBairro.Builder().bairro(vo.getNome()).cidade(vo.getCidade())
                    .build();

            session.getTransaction().begin();
            List<Bairro> bairros = session.createQuery(queryString.getSql()).list();
            session.getTransaction().commit();

            return bairros;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Bairro> pesquisaBairroLike(BairroFiltroVO vo) {
        try {
            QueryStringBairro queryString = new QueryStringBairro.Builder().bairroLike(vo.getNome()).cidade(vo.getCidade())
                    .build();

            session.getTransaction().begin();
            List<Bairro> bairros = session.createQuery(queryString.getSql()).list();
            session.getTransaction().commit();

            return bairros;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

}
