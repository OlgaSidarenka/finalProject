package com.sidarenka.alien.resource;

import java.util.ResourceBundle;

/**
 * The Class ConfigurationManager.
 */
public class ConfigurationManager {
    
    /** The Constant resourceBundle. */
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    
    /**
     * Instantiates a new configuration manager.
     */
    private ConfigurationManager() { }
    
    /**
     * Gets the property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key); }
}
