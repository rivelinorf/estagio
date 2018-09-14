package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.LogradouroDAO;
import br.com.sonner.estagio.dao.queries.QueryStringLogradouro;
import br.com.sonner.estagio.model.Logradouro;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.LogradouroFiltroVO;
import org.hibernate.Session;

import java.util.List;

public class LogradouroDAOImpl implements LogradouroDAO {

    private Session session;
    private static LogradouroDAO LOGRADOURO_DAO;

    public LogradouroDAOImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static LogradouroDAO getIntance() {
        if (LOGRADOURO_DAO == null) {
            LOGRADOURO_DAO = new LogradouroDAOImpl();
        }
        return LOGRADOURO_DAO;
    }

    @Override
    public Logradouro save(Logradouro logradouro) {
        try {
            session.beginTransaction();
            session.save(logradouro);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return logradouro;
    }
//        String sql = "insert into logradouro (nome,logradouro_cidade_fk,logradouro_tipo_fk) values (?,?,?)";
//        try {
//            PreparedStatement stmt = connection.prepareStatement(sql);
//
//            stmt.setString(1, logradouro.getNome());
//            stmt.setLong(2, logradouro.getCidade().getId());
//            stmt.setLong(3, logradouro.getTipologradouro().getId());
//
//            stmt.execute();
//            stmt.close();
//
//            return logradouro;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }


    @Override
    public List<Logradouro> getAll() {

        try {
            session.getTransaction().begin();
            List<Logradouro> logradouros = session.createQuery("select l from br.com.sonner.estagio.model.Logradouro as l").list();
            session.getTransaction().commit();

            return logradouros;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }

    }


//        try {
//            PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro");
//            ResultSet resultSet = stmt.executeQuery();
//
//            List<Logradouro> logradouros = new ArrayList<>();
//
//            while (resultSet.next()) {
//                CidadeController cidadeController = new CidadeControllerImpl();
//                TipoLogradouroController tipoLogradouroController = new TipoLogradouroControllerImpl();
//
//                TipoLogradouro tipologradouro = tipoLogradouroController
//                        .getOne(resultSet.getLong("logradouro_tipo_fk"));
//                Cidade cidade = cidadeController.getOne(resultSet.getLong("logradouro_cidade_fk"));
//
//                Logradouro logradouro = new Logradouro(resultSet.getString("nome"), tipologradouro, cidade);
//                logradouro.setId(resultSet.getLong("id"));
//                logradouro.setNome(resultSet.getString("nome"));
//                logradouro.getTipologradouro().setId(resultSet.getLong("logradouro_tipo_fk"));
//                logradouro.getCidade().setId(resultSet.getLong("logradouro_cidade_fk"));
//
//                logradouros.add(logradouro);
//            }
//
//            return logradouros;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public void update(Logradouro logradouro) {
        try {
            session.beginTransaction();
            session.update(logradouro);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

//        String sql = "UPDATE logradouro SET nome=?, logradouro_cidade_fk=?, logradouro_tipo_fk=? where id = ? ";
//        try {
//            PreparedStatement stmt = this.connection.prepareStatement(sql);
//            stmt.setString(1, logradouro.getNome());
//            stmt.setLong(2, logradouro.getCidade().getId());
//            stmt.setLong(3, logradouro.getTipologradouro().getId());
//            stmt.setLong(4, logradouro.getId());
//
//            stmt.execute();
//            stmt.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }

    @Override
    public void delete(Long id) {
        Logradouro logradouro = getOne(id);
        try {
            session.getTransaction().begin();
            session.remove(logradouro);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

    }


//        try {
//            PreparedStatement stmt = this.connection.prepareStatement("delete from logradouro where id=?");
//            stmt.setLong(1, id);
//            stmt.execute();
//            stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Logradouro getOne(Long id) {
        return session.find(Logradouro.class, id);

    }

//        try {
//            PreparedStatement stmt = this.connection.prepareStatement("select * from logradouro where id = ?");
//            stmt.setLong(1, id);
//
//            ResultSet resultSet = stmt.executeQuery();
//            TipoLogradouroDAO tipoLogradouroDAO = TipoLogradouroDAOImpl.getInstance();
//            CidadeDAO cidadeDAO = CidadeDAOImpl.getInstance();
//
//            Logradouro logradouro = null;
//
//            if (resultSet.first()) {
//                logradouro = new Logradouro();
//                logradouro.setId(resultSet.getLong("id"));
//                logradouro.setNome(resultSet.getString("nome"));
//                logradouro.setCidade(cidadeDAO.getOne(resultSet.getLong("logradouro_cidade_fk")));
//                logradouro.setTipologradouro(tipoLogradouroDAO.getOne(resultSet.getLong("logradouro_tipo_fk")));
//
//                stmt.close();
//            }
//
//            return logradouro;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public List<Logradouro> pesquisaLogradouro(LogradouroFiltroVO vo) {
        try {
            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder()
                    .logradouro(vo.getNome())
                    .tipologradouro(vo.getTipologradouro())
                    .cidade(vo.getCidade())
                    .build();

            session.getTransaction().begin();
            List<Logradouro> logradouros = session.createQuery(queryString.getSql()).list();
            session.getTransaction().commit();

            return logradouros;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }
//        try {
//            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder()
//                    .logradouro(vo.getNome())
//                    .tipologradouro(vo.getTipologradouro())
//                    .cidade(vo.getCidade())
//                    .build();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(queryString.getSql());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Logradouro> logradouros = new ArrayList<>();
//
//            while (resultSet.next()) {
//                Logradouro aux = new Logradouro();
//
//                aux.setId(resultSet.getLong("id"));
//                aux.setNome(resultSet.getString("nome"));
//                aux.setTipologradouro(TipoLogradouroDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_tipo_fk")));
//                aux.setCidade(CidadeDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_cidade_fk")));
//
//                logradouros.add(aux);
//            }
//
//            return logradouros;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    @Override
    public List<Logradouro> pesquisaLogradouroLike(LogradouroFiltroVO vo) {
        try {
            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder()
                    .logradouroLike(vo.getNome())
                    .tipologradouro(vo.getTipologradouro())
                    .cidade(vo.getCidade())
                    .build();

            session.getTransaction().begin();
            List<Logradouro> logradouros = session.createQuery(queryString.getSql()).list();
            session.getTransaction().commit();

            return logradouros;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

//
//        try {
//            QueryStringLogradouro queryString = new QueryStringLogradouro.Builder()
//                    .logradouroLike(vo.getNome())
//                    .tipologradouro(vo.getTipologradouro())
//                    .cidade(vo.getCidade())
//                    .build();
//            PreparedStatement preparedStatement = connection.prepareStatement(queryString.getSql());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Logradouro> logradouros = new ArrayList<>();
//
//            while (resultSet.next()) {
//                Logradouro aux = new Logradouro();
//
//                aux.setId(resultSet.getLong("id"));
//                aux.setNome(resultSet.getString("nome"));
//                aux.setTipologradouro(TipoLogradouroDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_tipo_fk")));
//                aux.setCidade(CidadeDAOImpl.getInstance().getOne(resultSet.getLong("logradouro_cidade_fk")));
//
//                logradouros.add(aux);
//            }
//
//            return logradouros;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}