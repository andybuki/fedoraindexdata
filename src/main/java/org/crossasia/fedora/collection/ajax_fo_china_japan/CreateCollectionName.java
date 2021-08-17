package org.crossasia.fedora.collection.ajax_fo_china_japan;

import org.crossasia.fedora.constants.FedoraCollections;
import org.crossasia.fedora.constants.FedoraDocumentation;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.fcrepo.client.PostBuilder;

import java.io.IOException;

import static org.crossasia.fedora.constants.Constants.*;

public class CreateCollectionName {
           public static void CreateCollection() {
               FcrepoClient client = FcrepoClient.client().build();
               try (FcrepoResponse response = new PostBuilder(FEDORA_URL_URI, client)
                       .slug(FedoraCollections.FO_CHINA_JAPAN.collection())
                       .body(EMPTY_FILE, CONTENT_TYPE)
                       .perform()) {
               } catch (IOException e) {
                   e.printStackTrace();
               } catch (FcrepoOperationFailedException e) {
                   e.printStackTrace();
               }
           }

            public static void CreateDocumentation() {
                FcrepoClient client = FcrepoClient.client().build();
                try (FcrepoResponse response = new PostBuilder(FEDORA_URL_URI, client)
                        .slug(FedoraCollections.FO_CHINA_JAPAN.collection()+"/"+ FedoraDocumentation.DOCUMENTATION.documentation())
                        .body(EMPTY_FILE, CONTENT_TYPE)
                        .perform()) {
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FcrepoOperationFailedException e) {
                    e.printStackTrace();
                }
            }
}
