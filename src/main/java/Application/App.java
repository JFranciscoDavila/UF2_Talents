package Application;
import org.hibernate.Session;

public class App {

	public static void main(String[] args) {

   		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
  		session.beginTransaction();
   		System.out.println("Hola desde Hibernate");
   		session.getTransaction().commit();
   		session.close();

}
}
