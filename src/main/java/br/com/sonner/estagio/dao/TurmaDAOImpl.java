package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.TurmaDAO;
import br.com.sonner.estagio.dao.queries.QueryStringTurma;
import br.com.sonner.estagio.model.Turma;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.TurmaFiltroVO;
import org.hibernate.Session;

import java.util.List;

public class TurmaDAOImpl implements TurmaDAO {

    private Session session;
    public static TurmaDAOImpl TURMA_DAO;

    public TurmaDAOImpl() {
    }

    public static TurmaDAOImpl getInstance() {
        if (TURMA_DAO == null) {
            TURMA_DAO = new TurmaDAOImpl();
        }
        return TURMA_DAO;
    }


    @Override
    public void save(Turma turma) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.save(turma);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Turma> getAll() {

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Turma> turmas = this.session.createQuery("select t from Turma as t").list();
            this.session.getTransaction().commit();

            return turmas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public void update(Turma turma) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.beginTransaction();
            this.session.update(turma);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.session.close();
        }
    }

    @Override
    public void delete(Long id) throws CustomException {
        Turma turma = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(turma);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! turma possui relacionamentos");
        } finally {
            this.session.close();
        }
    }

    @Override
    public Turma getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Turma.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    public List<Turma> pesquisaTurmaLike(TurmaFiltroVO vo) {
        try {
            QueryStringTurma queryString = new QueryStringTurma.Builder().turmaLike(vo.getNome()).escola(vo.getEscola())
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

    public List<Turma> pesquisaTurma(TurmaFiltroVO vo) {
        try {
            QueryStringTurma queryString = new QueryStringTurma.Builder().turma(vo.getNome())
                    .escola(vo.getEscola())
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
