package com.sidarenka.alien.resource;

import java.util.ResourceBundle;

public class DbResourceManager {
    private final static DbResourceManager instance=new DbResourceManager();
    private ResourceBundle bundle=ResourceBundle.getBundle("database");
    public static DbResourceManager getInstance(){
        return instance;
    }
    public String getValue(String key){
        return bundle.getString(key);
    }
}
