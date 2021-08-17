package org.crossasia.fedora;

import org.crossasia.fedora.model.FedoraConfig;
import org.fcrepo.client.DeleteBuilder;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.crossasia.fedora.constants.Constants.*;

public class FedoraDelete {
    FcrepoClient client = FcrepoClient.client().build();
    FcrepoClient client2 = new FcrepoClient.FcrepoClientBuilder().credentials("fedoraAdmin", "fedoraAdmin").build();
    static final URI PREFER_CONTAINMENT = URI.create("http://www.w3.org/ns/ldp#PreferContainment");

    /*static final Pattern childPattern = Pattern.compile(
            ".+?\\s+\"http:\\/\\/www.w3.org\\/ns\\/ldp#contains\"\\s+<(.+?)>.+?");*/

    //static final Pattern childPattern = Pattern.compile(".+?\"http://www.w3.org/ns/ldp#contains\".+?]");
    //static final Pattern childPatternx = Pattern.compile(".+?\"http://b-lx0147.sbb.spk-berlin.de:8088/rest\".+?]");
    static final Pattern childPattern = Pattern.compile(
            "(http://b-lx0147.sbb.spk-berlin.de:8088/rest/)([a-zA-Z0-9_.-]*[^:]\")");

    /*static final Pattern childPattern2 = Pattern.compile(
            "(http://b-lx0005.sbb.spk-berlin.de:8082/fcrepo/rest/ajax-fo-china-japan/)([a-zA-Z0-9_.-]*[^:]\")");*/

    public void fedoraDeleteContainer () throws FileNotFoundException {

        FedoraFileCall fedoraFileCall = new FedoraFileCall();
        ArrayList<String> urls = fedoraFileCall.FedoraFileCallDelete();

        for (int i=0; i<urls.size(); i++) {
            URI uri1 = URI.create(FEDORA_URL+"ajax-loc-gaz/"+urls.get(i)+"");
            URI uri_tombstone1 = URI.create(FEDORA_URL+"ajax-loc-gaz/"+urls.get(i)+"/fcr:tombstone");
            URI uri = URI.create(FEDORA_URL+urls.get(i)+"");
            URI uri_tombstone = URI.create(FEDORA_URL+urls.get(i)+"/fcr:tombstone");
            try {

                FcrepoResponse response1 = new DeleteBuilder(uri1, client).perform();
                FcrepoResponse response_tombstone1 = new DeleteBuilder(uri_tombstone1, client).perform();
                System.out.println("Resource deletion status: "+ response1.getStatusCode());
                System.out.println("Resource tombstone deletion status: "+ response_tombstone1.getStatusCode());

                FcrepoResponse response = new DeleteBuilder(uri, client).perform();
                FcrepoResponse response_tombstone = new DeleteBuilder(uri_tombstone, client).perform();
                System.out.println("Resource deletion status: "+ response.getStatusCode());
                System.out.println("Resource tombstone deletion status: "+ response_tombstone.getStatusCode());
            } catch (FcrepoOperationFailedException e) {
                e.printStackTrace();
            }

        }
    }

    public Collection<URI> getChildren(URI container) {
        try (final FcrepoResponse response = client.get(container)
                .accept(CONTENT_TYPE)
                .preferRepresentation(asList(PREFER_CONTAINMENT),
                        Collections.<URI>emptyList()).perform()) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody(), UTF_8))) {

                final List<URI> children = new ArrayList<>();

                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    final Matcher aclFinder = childPattern.matcher(line);
                    if (aclFinder.matches()) {
                        String test = aclFinder.group(1);
                        children.add(URI.create(aclFinder.group(1)));
                    }

                    Matcher matcher = childPattern.matcher(line);
                    while(matcher.find()) {
                        System.out.println("found: " + matcher.group(1));
                        children.add(URI.create(matcher.group(1)));
                    }
                    System.out.println(children.size());
                }


                System.out.println(children.size());
                return children;
            }

        } catch (final Exception e) {
            throw new RuntimeException("Error getting children of " + FEDORA_URL_URI, e);
        }
    }
}
