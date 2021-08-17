package org.crossasia.fedora.constants;

public enum FedoraServers {
    TEST("http://b-lx0147.sbb.spk-berlin.de:8888/fcrepo/rest/"),
    PROD("http://b-lx0005.sbb.spk-berlin.de:8082/fcrepo/rest"),
    PROD_ALIAS("https://itr02.crossasia.org/fcrepo/rest");

    private String server;

    FedoraServers(String server) {
        this.server = server;
    }

    public String server() {
        return server;
    }
}
