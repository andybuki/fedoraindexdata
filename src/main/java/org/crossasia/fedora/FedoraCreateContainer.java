package org.crossasia.fedora;

import org.crossasia.fedora.formater.VerySimpleFormatter;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.fcrepo.client.PostBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static org.crossasia.fedora.constants.Constants.*;


public class FedoraCreateContainer {
    private static VerySimpleFormatter formatterTxt;

    public static void main(String[] args) throws IOException {
        boolean append = true;
        FileHandler handler = new FileHandler("/opt/IdeaProjects/FedoraIndexData/src/main/resources/default.log", append);
        final Logger LOGGER = Logger.getLogger("FedoraCreateContainer.class");
        formatterTxt = new VerySimpleFormatter();
        handler.setFormatter(formatterTxt);
        LOGGER.addHandler(handler);


        FcrepoClient client = FcrepoClient.client().build();
        try (FcrepoResponse response = new PostBuilder(FEDORA_URL_URI, client)
                .slug("ajax-fo-china-japan6")
                .body(EMPTY_FILE, CONTENT_TYPE)
                .perform()) {

            URI location = response.getLocation();
            String status = "";
            if (response.getStatusCode()==201) {
                status = "OK";
                LOGGER.info("Added: " + status +" -> "+  location);
            } else {
                status = "ERROR in indexing";
                LOGGER.info("Error during index: " + status +"("+response.getLocation()+ ")-> "+  location);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FcrepoOperationFailedException e) {
            e.printStackTrace();
        }
    }
}
