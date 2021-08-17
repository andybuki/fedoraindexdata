package org.crossasia.fedora;

import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.fcrepo.client.PostBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.crossasia.fedora.constants.Constants.FEDORA_URL_URI;
import static org.crossasia.fedora.constants.Constants.FOLDER_FILES;

public class FedoraCreate {

    public void fedoraCreateContainer () throws FileNotFoundException {
        FcrepoClient client = FcrepoClient.client().build();
        //FedoraFileCall fedoraFileCall = new FedoraFileCall();
        //ArrayList<String> urls = fedoraFileCall.FedoraFileCallCreate();

        File[] files = FOLDER_FILES.listFiles();
        for (final File file : files) {
            String book = file.getName().replaceAll("\\d{1,}page_","").replace(".json","");
            try (FcrepoResponse response = new PostBuilder(FEDORA_URL_URI, client)
                    //82page_FO_371-83252_FOLDER_10_1950
                    .slug(book+"/"+file.getName().replace(".json",""))
                    .body(file, "application/ld+json")
                    .filename(file.getName())
                    .perform();) {
                System.out.println("Resource creation status: "+ response.getStatusCode());
            } catch (FcrepoOperationFailedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

