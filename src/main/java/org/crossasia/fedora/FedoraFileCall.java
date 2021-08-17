package org.crossasia.fedora;

import org.crossasia.fedora.entity.Containment;
import org.crossasia.fedora.model.Fedora;
import org.fcrepo.client.FcrepoClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.crossasia.fedora.constants.Constants.CREATE_FILE_PATH;
import static org.crossasia.fedora.constants.Constants.DELETE_FILE_PATH;

public class FedoraFileCall {


    public ArrayList<String> FedoraFileCallDelete() throws FileNotFoundException {
        Fedora fedora = new Fedora();
        FcrepoClient client = FcrepoClient.client().credentials("fedoraAdmin", "fedoraAdmin").build();
        URI uri = URI.create(fedora.getUrl());
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query query_parent=session.createQuery("FROM Containment");
            List<Containment> containmentList = (List<Containment>) ((org.hibernate.query.Query) query_parent).list();

            session.close();

            /*for(Containment containment: containmentList) {
                if (containment.getParent().equals("info:fedora/"+fedora.getCollection())) {
                    count_books++;
                }
                if (containment.getParent().contains(fedora.getCollection()+"/")) {
                    count_pages++;
                }
            }*/

        } finally {
            sessionFactory.close();
        }
        Scanner s = new Scanner(new File(DELETE_FILE_PATH));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        return list;
    }

    public ArrayList<String> FedoraFileCallCreate() throws FileNotFoundException {
        Scanner s = new Scanner(new File(CREATE_FILE_PATH));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        return list;
    }

}
