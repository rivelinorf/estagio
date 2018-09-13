package br.com.sonner.estagio.util;

import br.com.sonner.estagio.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Bairro.class)
                    .addAnnotatedClass(Cidade.class)
                    .addAnnotatedClass(Endereco.class)
                    .addAnnotatedClass(Estado.class)
                    .addAnnotatedClass(Logradouro.class)
                    .addAnnotatedClass(TipoLogradouro.class)
                    .addAnnotatedClass(Usuario.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    public static void main(String args[]) {


    }

}