package br.edu.faculdade.livraria.dao;

import br.edu.faculdade.livraria.model.Livro;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class LivroDAOImpl implements LivroDAO {

    private static SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;
    public static SessionFactory buildSessionFactory() {
        try {

            Configuration configuration = new Configuration();
            configuration.configure();
            //Use config file path explicitly 
            //configuration.configure("hibernate/hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory "
                    + "creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void adicionarLivro(Livro livro) {
        Transaction trns = null;
              Session session = getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(livro);
            session.getTransaction().commit();
        } catch (RuntimeException e) {            
            if (trns != null) trns.rollback();            
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<Livro> listarLivro() {
         return getSessionFactory().openSession().createCriteria(Livro.class).list();         
    }

    @Override
    public void alterarLivro(Livro livro) {
        Transaction trns = null;
        Session session = getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.merge(livro);
            session.getTransaction().commit();
        } catch (RuntimeException e) {            
            if (trns != null) trns.rollback();            
        } finally {
            session.flush();
            session.close();
        }   
    }

    @Override
    public void excluirLivro(Livro livro) {
        Transaction trns = null;
        Session session = getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.delete(livro);
            session.getTransaction().commit();
        } catch (RuntimeException e) {            
            if (trns != null) trns.rollback();            
        } finally {
            session.flush();
            session.close();
        }   
    }

}
