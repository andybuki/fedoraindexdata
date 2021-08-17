package org.crossasia.fedora;

import org.crossasia.fedora.entity.Containment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.FileNotFoundException;
import java.util.List;

public class FedoraMain2 {
    public static void main(String[] args) throws FileNotFoundException {

        FedoraDelete fedoraDelete = new FedoraDelete();
        fedoraDelete.fedoraDeleteContainer();

    }
}
