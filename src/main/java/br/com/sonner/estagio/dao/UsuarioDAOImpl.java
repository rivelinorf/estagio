package br.com.sonner.estagio.dao;

import br.com.sonner.estagio.dao.api.UsuarioDAO;
import br.com.sonner.estagio.dao.queries.QueryStringUsuario;
import br.com.sonner.estagio.model.Usuario;
import br.com.sonner.estagio.util.HibernateUtil;
import br.com.sonner.estagio.vos.UsuarioFiltroVo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Session session;
    private static UsuarioDAO USUARIO_DAO;

    private UsuarioDAOImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public static UsuarioDAO getInstance() {
        if (USUARIO_DAO == null) {
            USUARIO_DAO = new UsuarioDAOImpl();
        }

        return USUARIO_DAO;
    }

    @Override
    public void save(Usuario usuario) {
        try {
            session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public Usuario efetuaLogin(Usuario usuario) {
        try {
            session.beginTransaction();

            Query q = session.createQuery("select u from Usuario as u where u.usuario= :usuario and u.senha = :senha");
            q.setParameter("usuario", usuario.getUsuario());
            q.setParameter("senha", usuario.getSenha());
            List<Usuario> usuarios = q.list();


            Usuario aux;
            if (usuarios.get(0) != null) {
                aux = new Usuario();

                aux.setId(usuarios.get(0).getId());
                aux.setUsuario(usuarios.get(0).getUsuario());
                aux.setFoto(usuarios.get(0).getFoto());
                aux.setSenha(usuarios.get(0).getSenha());
                session.getTransaction().commit();

                return aux;
            }

            session.getTransaction().commit();
            return null;
        } catch (Exception e) {
            session.getTransaction().rollback();

        }
        return null;
    }


    @Override
    public List<Usuario> pesquisaUsuario(UsuarioFiltroVo vo) {
        try {
            QueryStringUsuario queryString = new QueryStringUsuario.Builder()
                    .usuario(vo.getUsuario())
                    .email(vo.getEmail())
                    .build();

            session.getTransaction().begin();
            List<Usuario> usuarios = session.createQuery(queryString.getSql()).list();
            session.getTransaction().commit();

            return usuarios;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void update(Usuario usuario) {
        try {
            session.beginTransaction();
            session.update(usuario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }


}
