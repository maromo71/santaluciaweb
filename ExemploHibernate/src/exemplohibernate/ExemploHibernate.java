package exemplohibernate;

import Model.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ExemploHibernate {

    public static void main(String[] args) {
        try {
            //Colar aqui ohhhhh
            //Criar um objeto
            Cliente c = new Cliente();
            c.setId(2);
            c.setNome("Mariana da Silva");
            c.setEndereco("Rua das Amoras, 23");
            c.setCpf("123456789-00");

            //Persistência - Estabelecer uma sessão
            Session session = Dao.DAOHibernateUtil.getSessionFactory().getCurrentSession();
            //Iniciar a Transacao
            session.beginTransaction();
            //Persistir o objeto cliente no banco
            session.save(c);
            //Dar um commit
            session.getTransaction().commit();
            System.out.println("Registro salvo com sucesso");
            System.exit(0);
        } catch (HibernateException e) {
            System.out.println("Aconteceu um erro: " + e.getMessage());
            System.exit(0);
        }
        
    }

}
