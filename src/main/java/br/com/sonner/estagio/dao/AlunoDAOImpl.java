package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.AlunoDAO;
import br.com.sonner.estagio.dao.queries.QueryStringAluno;
import br.com.sonner.estagio.dao.queries.QueryStringFuncionario;
import br.com.sonner.estagio.model.Aluno;
import br.com.sonner.estagio.model.Funcionario;
import br.com.sonner.estagio.model.Pessoa;
import br.com.sonner.estagio.model.TurmaDisciplina;
import br.com.sonner.estagio.util.CustomException;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.AlunoFiltroVO;
import br.com.sonner.estagio.vos.TurmaDisciplinaFiltroVO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AlunoDAOImpl implements AlunoDAO {
    private Session session;
    public static AlunoDAOImpl DIRETOR_DAO;

    public AlunoDAOImpl() {
    }

    public static AlunoDAOImpl getInstance() {
        if (DIRETOR_DAO == null) {
            DIRETOR_DAO = new AlunoDAOImpl();
        }
        return DIRETOR_DAO;
    }

    @Override
    public void save(Aluno aluno) {
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
    public void update(Aluno aluno) {
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
    public List<Aluno> getAll() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            List<Aluno> alunos = this.session.createQuery("select a from Aluno as a").list();
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
        Aluno aluno = getOne(id);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            this.session.remove(aluno);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            throw new CustomException("Impossível deletar! aluno possui relacionamentos");
        } finally {
            this.session.close();
        }

    }

    @Override
    public Aluno getOne(Long id) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            return this.session.find(Aluno.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }

    @Override
    public List<Aluno> pesquisaAluno(AlunoFiltroVO vo) {
        try {
            QueryStringAluno queryString = new QueryStringAluno.Builder().table("Aluno")
                    .nome(vo.getPessoa().getNome())
                   // .sexo(String.valueOf(vo.getPessoa().getSexo()))
                    //.matricula(String.valueOf(vo.getPessoa().getAluno().getMatricula()))
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


    @Override
    public List<Aluno> pesquisaAlunoLike(AlunoFiltroVO vo) {
        try {
            QueryStringAluno queryString = new QueryStringAluno.Builder()
                    .table("Aluno")
                    .nomeLike(vo.getPessoa().getNome())
                    //.sexo(String.valueOf(vo.getPessoa().getSexo()))
                    //.matriculaLike(String.valueOf(vo.getPessoa().getAluno().getMatricula()))
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

    @Override
    public List<Aluno> pesquisaAlunoPorTurmaDisciplina(TurmaDisciplinaFiltroVO turmaDisciplinaFiltroVO) {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.session.getTransaction().begin();
            Query query = this.session.createQuery("select aluno from TurmaDisciplina as td " +

                    "inner join td.turma " +
                    "inner join td.disciplina " +
                    "inner join td.turma.matriculas as matricula " +
                    "inner join matricula.aluno as aluno " +
                    "where td.disciplina.id = :disciplina and td.turma.id = :turma"
            );
            query.setParameter("disciplina", turmaDisciplinaFiltroVO.getDisciplina());
            query.setParameter("turma", turmaDisciplinaFiltroVO.getTurma());
            List<Aluno> alunos = query.list();
            this.session.getTransaction().commit();

            return alunos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.session.close();
        }
    }
}
