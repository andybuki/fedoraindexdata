package org.crossasia.fedora.controller;

import org.crossasia.fedora.constants.FedoraCollections;
import org.crossasia.fedora.constants.FedoraServers;
import org.crossasia.fedora.formater.VerySimpleFormatter;
import org.crossasia.fedora.model.Fedora;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.fcrepo.client.PostBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.URI;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static org.crossasia.fedora.constants.Constants.*;

@Controller
public class ConfigurationController {

    private static VerySimpleFormatter formatterTxt=new VerySimpleFormatter();
    final Logger LOGGER = Logger.getLogger("ConfigurationController.class");
    boolean append = true;
    FileHandler handler =new FileHandler("/opt/IdeaProjects/FedoraIndexData/src/main/resources/default.log", append);

    public static int count = 0;

    public ConfigurationController() throws IOException {
    }


    @RequestMapping("/askSettings")
    public String askSettings (Model model) {
        Fedora fedora = new Fedora();
        fedora.setUrl(FedoraServers.TEST.server());
        fedora.setCollection(FedoraCollections.RMRB.collection());
        model.addAttribute("fedora", fedora);

        return "index/fedora-settings";
    }


    @RequestMapping("/showSettings")
    public String showSettings (@ModelAttribute("fedora") Fedora fedora) throws IOException {
        //BufferedWriter out = new BufferedWriter(new FileWriter("/mnt/b-isiprod-udl.pk.de/itr/archive/fedora/dlntm/files.sh"));
        PrintStream out = new PrintStream(new FileOutputStream("/mnt/b-isiprod-udl.pk.de/itr/archive/fedora/dlntm/files.sh"));
        FcrepoClient client = FcrepoClient.client().credentials("fedoraAdmin", "fedoraAdmin").build();
        URI uri = URI.create(fedora.getUrl());
        //------------------------CREATE COLLECTION------------------------------------
        try (FcrepoResponse response = new PostBuilder(uri, client)
                .slug(fedora.getCollection())
                .body(EMPTY_FILE, CONTENT_TYPE)
                .perform()) {

            URI location = response.getLocation();

            handler.setFormatter(formatterTxt);
            LOGGER.addHandler(handler);

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
        //------------------------END CREATE COLLECTION--------------------------------
        FcrepoClient clientBooks = FcrepoClient.client().credentials("fedoraAdmin", "fedoraAdmin").build();

        File wholePath = new File(FOLDER_FILES +"/"+fedora.getCollection()+"/"+fedora.getPath());
        File[] files = wholePath.listFiles();
        String cURLink ="";
        String fedoraDocumentType = fedora.getDocument();
        for (final File file : files) {
            JSONObject jsonObjPage = new JSONObject(new JSONTokener(new FileInputStream(file)));
            JSONArray jsonArray = jsonObjPage.getJSONArray("@graph");
            JSONObject graphObj = jsonArray.getJSONObject(0);

            String book = "";
            String page_id = "";
            String url = "";
            String original_file_name = "";
            if (fedoraDocumentType.equals("books")) {
                book = "";
                url = fedora.getCollection()+"/"+file.getName().replace(".json","");
            } else if (fedoraDocumentType.equals("pages")) {

                if (graphObj.has("schema:image")) {
                    original_file_name = (String) graphObj.get("schema:image").toString();
                }

                book = file.getName().replace(".json","");
                page_id = book.split("_")[1];
                book = book.split("_")[0];

                url = fedora.getCollection()+"/"+book+"/"+file.getName().replace(".json","");
            } else if (fedoraDocumentType.equals("images")) {
                book = "";
                url = "";
            }

            cURLink = "curl -u fedoraAdmin:fedoraAdmin -i -X PUT --data-binary" + " @/mnt/b-isiprod-udl.pk.de/itr/archive/raw_collections/DLNTM/user_file/" + page_id + "/" +  original_file_name + " -H " + QUOTE + "Content-Type: image/jpg" + QUOTE + " -H \"Content-Disposition: attachment; filename=" + original_file_name + QUOTE+" " + "https://itr02.crossasia.org/fcrepo/rest/dlntm/"  + book + "/" + page_id+"/" + original_file_name.replace(".jpg","") + "/image";
            out.println(cURLink);

            try (FcrepoResponse response = new PostBuilder(uri, clientBooks)
                    //82page_FO_371-83252_FOLDER_10_1950
                    .slug(url)
                    //.slug(fedora.getCollection()+"/"+book+"/"+file.getName().replace(".json",""))
                    //.slug(fedora.getCollection()+"/"+file.getName().replace(".json",""))
                    .body(file, "application/ld+json")
                    .filename(file.getName())
                    .perform();) {

                if (response.getStatusCode()==201) {
                    System.out.println("Resource creation status: "+ response.getStatusCode());
                    count ++;
                } else {
                    System.out.println("Resource creation status: "+ response.getStatusCode());
                }
            } catch (FcrepoOperationFailedException | IOException e) {
                e.printStackTrace();
            }
            System.out.println(count);
        }

        return "index/show-fedora";
    }
}
