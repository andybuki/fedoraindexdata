package org.crossasia.fedora.model;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoClient.FcrepoClientBuilder;
import org.fcrepo.client.FcrepoResponse;

public class FcrepoLister {

    FcrepoClient client = new FcrepoClient.FcrepoClientBuilder().credentials(FedoraConfig.getUserName(), FedoraConfig
            .getPassword()).build();

    static final URI PREFER_CONTAINMENT = URI.create("http://www.w3.org/ns/ldp#PreferContainment");

    static final Pattern childPattern = Pattern.compile(
            ".+?\\s+<http://www.w3.org/ns/ldp#contains>\\s+<(.+?)>.+?");

    /*@Override
    public Collection<URI> getChildren(URI container) {
        try (final FcrepoResponse response = client.get(resource)
                .accept("application/n-triples")
                .preferRepresentation(asList(PREFER_CONTAINMENT),
                        emptyList()).perform()) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody(), UTF_8))) {

                final List<URI> children = new ArrayList<>();

                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    final Matcher aclFinder = childPattern.matcher(line);
                    if (aclFinder.matches()) {
                        children.add(URI.create(aclFinder.group(1)));
                    }
                }

                return children;
            }

        } catch (final Exception e) {
            throw new RuntimeException("Error getting children of " + resource, e);
        }
    }*/
}
