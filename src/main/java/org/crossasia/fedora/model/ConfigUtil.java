package org.crossasia.fedora.model;


public class ConfigUtil {

    /**
     * Retrieve property from a system property or renvironment variable or set to default
     * <p>
     * Given a string as a system property name (and a default) will:
     * </p>
     * <ol>
     * <li>Look up the system property with the matching name, and return it if defined</li>
     * <li>Try to match an environment variable matching the key transformed TO_UPPER_CASE with periods substituted
     * with underscores</li>
     * <li>Use the default of none others match</li>
     * </ol>
     *
     * @param key - property/variable name in property-normal form (period separators, ideally all lowercas)
     * @param defaultValue Default value
     * @return The value.
     */
    public static String getSystemProperty(final String key, final String defaultValue) {
        //return System.getProperty(key, System.getenv().getOrDefault(toEnvName(key), defaultValue));
        return null;
    }

    static String toEnvName(String name) {
        return name.toUpperCase().replace('.', '_');
    }
}