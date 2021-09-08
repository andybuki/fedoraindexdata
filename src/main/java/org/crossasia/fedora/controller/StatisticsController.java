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
import java.math.BigInteger;
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
        String collectionName = fedora.getCollection();
        BigInteger count_books = BigInteger.valueOf(0);
        BigInteger count_pages = BigInteger.valueOf(0);
        BigInteger count_images = BigInteger.valueOf(0);

        FcrepoClient client = FcrepoClient.client().credentials("fedoraAdmin", "fedoraAdmin").build();
        URI uri = URI.create(fedora.getUrl());
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try {
            Session booksSession = sessionFactory.openSession();
            Session imagesSession = sessionFactory.openSession();
            Session pagesSession = sessionFactory.openSession();
            booksSession.beginTransaction();
            imagesSession.beginTransaction();
            pagesSession.beginTransaction();

            count_images = (BigInteger) imagesSession.createNativeQuery("select count (*) FROM Containment where fedora_id" +
                            " like '%/image%'"+
                            "and fedora_id like :collectionName")
                    .setParameter("collectionName", '%'+collectionName+'%')
                    .getSingleResult();

            count_books = (BigInteger) booksSession.createNativeQuery("select count (*) FROM Containment where parent=:collectionName")
                    .setParameter("collectionName", "info:fedora/"+collectionName)
                    .getSingleResult();

            count_pages = (BigInteger) pagesSession.createNativeQuery("select count (*) FROM Containment where not fedora_id" +
                            " like '%/image%'"+
                            "and fedora_id like :collectionName")
                    .setParameter("collectionName", '%'+collectionName+'%')
                    .getSingleResult();
            //where not (fedora_id like '%/image%') and fedora_id like '%dllm%'

            booksSession.close();
            imagesSession.close();
            pagesSession.close();

        } finally {
            sessionFactory.close();
        }
        model.addAttribute("count_books",count_books);
        model.addAttribute("count_pages",count_pages);
        model.addAttribute("count_images",count_images);
        return "statistics/fedora-statistics-display";
    }
}
