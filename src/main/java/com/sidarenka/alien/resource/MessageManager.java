package com.sidarenka.alien.resource;

import java.util.ResourceBundle;

/**
 * The Class MessageManager.
 */
public class MessageManager {
    
    /** The Constant resourceBundle. */
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
    
    /**
     * Instantiates a new message manager.
     */
    private MessageManager() { }
    
    /**
     * Gets the property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
