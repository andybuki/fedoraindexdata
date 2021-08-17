package org.crossasia.fedora;

import org.crossasia.fedora.entity.Containment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.FileNotFoundException;
import java.util.List;

public class FedoraMain {
    public static void main(String[] args) throws FileNotFoundException {

        /*FedoraCreate fedoraCreate = new FedoraCreate();
        try {
            fedoraCreate.fedoraCreateContainer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateCollectionName.CreateCollection();
        CreateCollectionName.CreateDocumentation();

  FedoraDelete fedoraDelete = new FedoraDelete();
  fedoraDelete.fedoraDeleteContainer();
URI FEDORA_URL_URI2= URI.create( "http://b-lx0005.sbb.spk-berlin.de:8082/fcrepo/rest/ajax-fo-china-japan");
        FedoraDelete fedoraDelete = new FedoraDelete();

        fedoraDelete.getChildren(FEDORA_URL_URI);

  */



        /*SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                //.configure()
                .addAnnotatedClass(Containment.class)
                .buildSessionFactory();*/

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();



        //try {
            //Session session = sessionFactory.getCurrentSession();
            //Containment containment = new Containment();

            //session.beginTransaction();

            Query query=session.createQuery("FROM Containment");
            List<Containment> containmentList = (List<Containment>) ((org.hibernate.query.Query) query).list();

            session.close();

            int count = 0;

            for(Containment containment: containmentList) {
                System.out.println("AAAAAAAAAA____" + containment.getFedora_id() + " - " + containment.getParent());
                count++;

            }
        System.out.println("Collection size: " + count);
            //query.setFirstResult(2);
            /*List <Containment>list=query.list();

            List<Containment> containmentList = session.createQuery("from Containment")
                    .getResultList();
            System.out.println("test");
            for (Containment containment: containmentList) {
                System.out.println("TEST" + containment);
            }*/



            //Containment containment =  session.get(Containment.class, "info:fedora/test");
            //System.out.println("TEST"+ containment.getParent());
            //session.getTransaction().commit();

        /*} catch(HibernateException exception){
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
        } finally {
            sessionFactory.close();
        }*/
    }
}
