package org.crossasia.fedora.constants;

public enum FedoraCollections {
    RMRB("ajax-rmrb"),
    MINGUO("ajax-minguo"),
    FO_CHINA_JAPAN("ajax-fo-china-japan"),
    DFZ("ajax-dfz"),
    LOCAL_GAZZETTER("ajax-loc-gaz");

    private String collection;

    FedoraCollections(String collection) {
        this.collection = collection;
    }

    public String collection() {
        return collection;
    }
}
