package org.crossasia.fedora.constants;

import java.io.File;
import java.net.URI;

public class Constants {
    public final static URI FEDORA_URL_URI= URI.create("http://b-lx0147.sbb.spk-berlin.de:8888/fcrepo/rest");
    public final static File EMPTY_FILE = new File("/opt/IdeaProjects/FedoraIndexData/src/main/resources/emptyFile.json");
    public final static String PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public final static String CONTENT_TYPE = "application/ld+json";

    public final static String DELETE_FILE_PATH="/opt/IdeaProjects/FedoraIndexData/src/main/resources/deletedList.txt";
    public final static String CREATE_FILE_PATH="/opt/IdeaProjects/FedoraIndexData/src/main/resources/deletedList.txt";

    public final static String FEDORA_URL="http://b-lx0147.sbb.spk-berlin.de:8888/fcrepo/rest/";

    public final static File FOLDER_FILES = new File("/mnt/b-isiprod-udl.pk.de/itr/archive/fedora/");
}
