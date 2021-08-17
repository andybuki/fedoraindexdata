package org.crossasia.fedora.constants;

public enum FedoraDocumentation {

    DOCUMENTATION("documentation"),
    ARCHIVE("archive"),
    METADATA_ORIGINAL("metadata-original"),
    BOOKS("books"),
    ARTICLES("articles"),
    PAGES("pages"),
    IMAGES("images"),
    PDFS("pdfs"),
    XMLS("xmls");

    private String documentation;

    FedoraDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String documentation() {
        return documentation;
    }
}
