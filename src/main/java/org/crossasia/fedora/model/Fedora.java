package org.crossasia.fedora.model;

import org.crossasia.fedora.constants.FedoraCollections;
import org.crossasia.fedora.constants.FedoraDocumentation;
import org.crossasia.fedora.constants.FedoraPath;
import org.crossasia.fedora.constants.FedoraServers;

import java.util.HashMap;
import java.util.Map;

public class Fedora {

    private String url;
    private Map<String, String > urls;

    private String collection;
    private Map<String, String > collections;

    private String document;
    private Map<String, String > documents;

    private String path;
    private Map<String, String > paths;

    public Fedora() {
        urls = new HashMap<>();
        urls.put(FedoraServers.TEST.server(), FedoraServers.TEST.name());
        urls.put(FedoraServers.PROD.server(), FedoraServers.PROD.name());
        urls.put(FedoraServers.PROD_ALIAS.server(), FedoraServers.PROD_ALIAS.name());

        collections = new HashMap<>();
        collections.put(FedoraCollections.FO_CHINA_JAPAN.collection(), FedoraCollections.FO_CHINA_JAPAN.name());
        collections.put(FedoraCollections.RMRB.collection(), FedoraCollections.RMRB.name());
        collections.put(FedoraCollections.LOCAL_GAZZETTER.collection(), FedoraCollections.LOCAL_GAZZETTER.name());
        collections.put(FedoraCollections.DFZ.collection(), FedoraCollections.DFZ.name());
        collections.put(FedoraCollections.DLLM.collection(), FedoraCollections.DLLM.name());
        collections.put(FedoraCollections.DLNTM.collection(), FedoraCollections.DLNTM.name());
        collections.put(FedoraCollections.MINGUO.collection(), FedoraCollections.MINGUO.name());

        documents = new HashMap<>();
        documents.put(FedoraDocumentation.BOOKS.documentation(), FedoraDocumentation.BOOKS.name());
        documents.put(FedoraDocumentation.PAGES.documentation(), FedoraDocumentation.PAGES.name());
        documents.put(FedoraDocumentation.PDFS.documentation(), FedoraDocumentation.PDFS.name());
        documents.put(FedoraDocumentation.ARTICLES.documentation(), FedoraDocumentation.ARTICLES.name());
        documents.put(FedoraDocumentation.IMAGES.documentation(), FedoraDocumentation.IMAGES.name());
        documents.put(FedoraDocumentation.XMLS.documentation(), FedoraDocumentation.XMLS.name());
        documents.put(FedoraDocumentation.DOCUMENTATION.documentation(), FedoraDocumentation.DOCUMENTATION.name());
        documents.put(FedoraDocumentation.METADATA_ORIGINAL.documentation(), FedoraDocumentation.METADATA_ORIGINAL.name());
        documents.put(FedoraDocumentation.ARCHIVE.documentation(), FedoraDocumentation.ARCHIVE.name());

        paths = new HashMap<>();
        paths.put(FedoraPath.PATH_TO_BOOKS.path(), FedoraPath.PATH_TO_BOOKS.name());
        paths.put(FedoraPath.PATH_TO_ARTICLES.path(), FedoraPath.PATH_TO_ARTICLES.name());
        paths.put(FedoraPath.PATH_TO_PAGES.path(), FedoraPath.PATH_TO_PAGES.name());
        paths.put(FedoraPath.PATH_TO_IMAGES.path(), FedoraPath.PATH_TO_IMAGES.name());
        paths.put(FedoraPath.PATH_TO_PDFS.path(), FedoraPath.PATH_TO_PDFS.name());
        paths.put(FedoraPath.PATH_TO_XMLS.path(), FedoraPath.PATH_TO_XMLS.name());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }


    public Map<String, String> getCollections() {
        return collections;
    }

    public void setCollections(Map<String, String> collections) {
        this.collections = collections;
    }

    public Map<String, String> getDocuments() {
        return documents;
    }

    public void setDocuments(Map<String, String> documents) {
        this.documents = documents;
    }

    public Map<String, String> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, String> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "Fedora{" +
                "url='" + url + '\'' +
                ", collection='" + collection + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
