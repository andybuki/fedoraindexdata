package org.crossasia.fedora.constants;

public enum FedoraPath {
    PATH_TO_BOOKS("/books"),
    PATH_TO_PAGES("/pages"),
    PATH_TO_ARTICLES("/articles"),
    PATH_TO_PDFS("/pdfs"),
    PATH_TO_XMLS("/xmls"),
    PATH_TO_IMAGES("/images");

    private String path;

    FedoraPath(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
