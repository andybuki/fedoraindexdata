package org.crossasia.fedora.model;

import java.net.URI;
import java.util.Collection;

public interface Lister {
    Collection<URI> getChildren(URI container);
}
