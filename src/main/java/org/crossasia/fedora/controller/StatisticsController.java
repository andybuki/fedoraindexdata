package org.crossasia.fedora.controller;

import org.crossasia.fedora.constants.FedoraCollections;
import org.crossasia.fedora.entity.Containment;
import org.crossasia.fedora.model.Fedora;
import org.fcrepo.client.FcrepoClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Query;
import java.net.URI;
import java.util.List;

@Controller
public class StatisticsController {

    @RequestMapping("/statistics")
    public String statistics (Model model) {
        Fedora fedora = new Fedora();
        fedora.setCollection(FedoraCollections.RMRB.collection());
        model.addAttribute("fedora", fedora);

        return "statistics/fedora-statistics";
    }


    @RequestMapping("/statistics_show")
    public String statistics_show (@ModelAttribute("fedora") Fedora fedora, Model model) {
        int count_books = 0;
        int count_pages = 0;
        int count_images = 0;
        FcrepoClient client = FcrepoClient.client().credentials("fedoraAdmin", "fedoraAdmin").build();
        URI uri = URI.create(fedora.getUrl());
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query query_parent=session.createQuery("FROM Containment");
            List<Containment> containmentList = (List<Containment>) ((org.hibernate.query.Query) query_parent).list();

            session.close();

            for(Containment containment: containmentList) {
                if (containment.getParent().equals("info:fedora/"+fedora.getCollection())) {
                    count_books++;
                }
                if (containment.getParent().contains(fedora.getCollection()+"/")) {
                    count_pages++;
                }
            }

        } finally {
            sessionFactory.close();
        }
        model.addAttribute("count_books",count_books);
        model.addAttribute("count_pages",count_pages);
        model.addAttribute("count_images",count_images);
        return "statistics/fedora-statistics-display";
    }
}
