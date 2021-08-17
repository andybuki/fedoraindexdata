package org.crossasia.fedora.model;

public class FedoraConfig {
    //private static final Logger LOG = LoggerFactory.getLogger(FedoraConfig.class);

    private static final String USER_KEY = "pass.fedora.user";
    private static final String DEFAULT_USER  = "fedoraAdmin";

    private static final String PWD_KEY = "pass.fedora.password";
    private static final String DEFAULT_PASSWORD = "moo";

    private static final String BASEURL_KEY = "pass.fedora.baseurl";
    private static final String DEFAULT_BASE_URL = "http://localhost:8080/fcrepo/rest/";


    /**
     * Get the Fedora baseUrl
     *
     * @return the baseURL
     */
    public static String getBaseUrl() {
        String baseUrl = ConfigUtil.getSystemProperty(BASEURL_KEY, DEFAULT_BASE_URL);
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        //LOG.debug("Using baseUrl: {}", baseUrl);
        return baseUrl;
    }

    /**
     * Retrieve the user name from a system property, or use default
     * @return user name
     */
    public static String getUserName() {
        String username = ConfigUtil.getSystemProperty(USER_KEY, DEFAULT_USER);
        //LOG.debug("Using user name: {}", username);
        return username;
    }

    /**
     * Retrieve the password from a system property, or use default
     * @return user name
     */
    public static String getPassword() {
        String user = ConfigUtil.getSystemProperty(PWD_KEY, DEFAULT_PASSWORD);
        return user;
    }

    /**
     * Get a path for a container, given a PASS type
     *
     * @param type String name of the PASS type.
     * @return the container path
     */
    /*public static String getContainer(String type) {
        String path = null;
        PassEntityType subdirectory = PassEntityType.getTypeByName(type);
        if (subdirectory!=null) {
            path = getBaseUrl() + subdirectory.getPlural();
        } else {
            throw new IllegalArgumentException("Type not recognized, container path not found.");
        }

        //LOG.debug("Returning container path: {}", path);
        return path;
    }*/
}
