package com.sidarenka.alien.validator;

/**
 * The Class AlienValidator.
 */
public class AlienValidator {
    
    /** The Constant ALIEN_NAME_PATTERN. */
    private static final String ALIEN_NAME_PATTERN = "[\\w\\s+\\.]{1,20}";
    
    /** The Constant ALIEN_HOMELAND_PATTERN. */
    private static final String ALIEN_HOMELAND_PATTERN="[\\w\\s]{1,25}";
    
    /** The Constant ALIEN_DESCRIPTION_PATTERN. */
    private static final String ALIEN_DESCRIPTION_PATTERN="[\\w\\s\\p{Punct}]{2,1000}";

    /**
     * Validate alien data.
     *
     * @param name the name
     * @param homeland the homeland
     * @param description the description
     * @return true, if successful
     */
    public static boolean validateAlienData(String name, String homeland, String description){
        return (name.matches(ALIEN_NAME_PATTERN)&&homeland.matches(ALIEN_HOMELAND_PATTERN)
                &&description.matches(ALIEN_DESCRIPTION_PATTERN));
    }
    
    /**
     * Validate alien description.
     *
     * @param description the description
     * @return true, if successful
     */
    public static boolean validateAlienDescription(String description){
        return (description.matches(ALIEN_DESCRIPTION_PATTERN));
    }
    
    /**
     * Validate alien homeland.
     *
     * @param homelandName the homeland name
     * @return true, if successful
     */
    public static boolean validateAlienHomeland(String homelandName){
        return (homelandName.matches(ALIEN_HOMELAND_PATTERN));
    }
}
