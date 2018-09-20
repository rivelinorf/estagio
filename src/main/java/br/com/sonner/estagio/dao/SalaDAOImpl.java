package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.SalaDAO;
import br.com.sonner.estagio.dao.queries.QueryStringSala;
import br.com.sonner.estagio.model.Sala;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.SalaFiltroVO;
import org.hibernate.Session;

import java.util.List;

public class SalaDAOImpl implements SalaDAO {
    private Session session;
    public static SalaDAOImpl SALA_DAO;

    public SalaDAOImpl() {
    }

    public static SalaDAOImpl getInstance() {
        if (SALA_DAO == null) {
            SALA_DAO = new SalaDAOImpl();
        }
        return SALA_DAO;
    }

    @Override
    public void save(Sala sala) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(sala);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }

    }

    @Override
    public List<Sala> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Sala> salas = this.session.createQuery("select s from Sala as s").list();
            this.session.getTransaction().commit();

            return salas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Sala sala) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(sala);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }

    }

    @Override
    public void delete(Long id) throws CustomException {
        Sala sala = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(sala);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! Sala possui relacionamentos");
        } finally {
            this.session.close();
        }
    }

    @Override
    public Sala getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Sala.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Sala> pesquisaSalaLike(SalaFiltroVO vo) {
        try {
            QueryStringSala queryStringSala = new QueryStringSala.Builder().salaLike(vo.getNome()).escola(vo.getEscola()).build();
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.createQuery(queryStringSala.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Sala> pesquisaSala(SalaFiltroVO vo) {
        try {
            QueryStringSala queryStringSala = new QueryStringSala.Builder().sala(vo.getNome())
                    .escola(vo.getEscola())
                    .build();
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.createQuery(queryStringSala.getSql()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }


    }
}
