package com.sidarenka.alien.validator;

public class AlienValidator {
    private static final String ALIEN_NAME_PATTERN = "[\\w\\s]{1,20}";
    private static final String ALIEN_HOMELAND_PATTERN="[\\w\\s]{1,25}";
    private static final String ALIEN_DESCRIPTION_PATTERN="[\\w\\s\\s\\p{Punct}]{2,100}";

    public static boolean validateAlienData(String name, String homeland, String description){
        return (name.matches(ALIEN_NAME_PATTERN)&&homeland.matches(ALIEN_HOMELAND_PATTERN)
                &&description.matches(ALIEN_DESCRIPTION_PATTERN));
    }
    public static boolean validateAlienDescription(String description){
        return (description.matches(ALIEN_DESCRIPTION_PATTERN));
    }
}
