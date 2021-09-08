package org.crossasia.fedora.controller;

import org.crossasia.fedora.constants.FedoraCollections;
import org.crossasia.fedora.constants.FedoraServers;
import org.crossasia.fedora.entity.Containment;
import org.crossasia.fedora.model.Fedora;
import org.fcrepo.client.DeleteBuilder;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Query;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeleteCollectionController {

    FcrepoClient client = new FcrepoClient.FcrepoClientBuilder().credentials("fedoraAdmin", "fedoraAdmin").build();

    @RequestMapping("/deleteCollection")
    public String askSettings (Model model) {
        Fedora fedora = new Fedora();
        fedora.setUrl(FedoraServers.TEST.server());
        fedora.setCollection(FedoraCollections.RMRB.collection());
        model.addAttribute("fedora", fedora);

        return "delete/fedora-collection-delete";
    }

    @RequestMapping("/showCollectionDelete")
    public String showSettings (@ModelAttribute("fedora") Fedora fedora, Model model) throws IOException {

        URI uri = URI.create(fedora.getUrl());
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        ArrayList<String> urls = new ArrayList<>();
        int deleted_objects = 0;

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query query_parent=session.createQuery("FROM Containment");
            List<Containment> containmentList = (List<Containment>) ((org.hibernate.query.Query) query_parent).list();

            session.close();

            for(Containment containment: containmentList) {
                /*if (containment.getParent().equals("info:fedora/"+fedora.getCollection())) {
                    urls.add(containment.getParent());
                }*/
                if (containment.getFedora_id().contains(fedora.getCollection())) {
                   urls.add(containment.getFedora_id());
                }
            }

        } finally {
            sessionFactory.close();
        }

        for (int i=0; i<urls.size(); i++) {
            URI uri_del = URI.create(fedora.getUrl()+ urls.get(i).replace("info:fedora/", ""));
            URI uri_tombstone = URI.create(fedora.getUrl()+ urls.get(i).replace("info:fedora/", "")+"/fcr:tombstone");

            try {
                FcrepoResponse response = new DeleteBuilder(uri_del, client).perform();
                FcrepoResponse response_tombstone = new DeleteBuilder(uri_tombstone, client).perform();
                System.out.println("Resource deletion status: "+ response.getStatusCode());
                System.out.println("Resource tombstone deletion status: "+ response_tombstone.getStatusCode());

            } catch (FcrepoOperationFailedException e) {
                e.printStackTrace();
            }
        }
        deleted_objects = urls.size();
        model.addAttribute("deleted_objects",deleted_objects);
        return "delete/show-deleted-fedora-collection";
    }
}
