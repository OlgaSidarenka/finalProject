package com.sidarenka.alien.command;

import com.sidarenka.alien.command.impl.*;

import java.util.EnumMap;

/**
 * The Class CommandMap.
 */
public class CommandMap {
    
    /** The command map. */
    private EnumMap<CommandType, Command> commandMap = new EnumMap<CommandType, Command>(CommandType.class){
        {
            this.put(CommandType.LOGIN, new LogInCommand());
            this.put(CommandType.LOGOUT, new LogoutCommand());
            this.put(CommandType.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
            this.put(CommandType.GO_TO_START_PAGE, new StartPageCommand());
            this.put(CommandType.REGISTRATION, new RegistrationCommand());
            this.put(CommandType.SEE_REVIEWS, new ShowReviewsCommand());
            this.put(CommandType.ADD_REVIEW, new AddReviewCommand());
            this.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
            this.put(CommandType.SHOW_USERS, new ShowUsersCommand());
            this.put(CommandType.SHOW_ALIENS, new ShowAliensCommand());
            this.put(CommandType.FILL_NEW_ALIEN_DATA, new FillNewAlienDataCommand());
            this.put(CommandType.ADD_ALIEN, new AddAlienCommand());
            this.put(CommandType.CHANGE_USER_STATUS, new ChangeUserStatusCommand());
            this.put(CommandType.RATE_ALIEN, new RateAlienCommand());
            this.put(CommandType.SHOW_ALIEN_BY_NAME, new ShowAlienByNameCommand());
            this.put(CommandType.UPDATE_ALIEN, new UpdateAlienCommand());
            this.put(CommandType.SEE_USER_REVIEWS, new ViewUserReviewsCommand());
            this.put(CommandType.BLOCK_REVIEW, new BlockReviewCommand());
            this.put(CommandType.ADD_HOMELAND, new AddHomelandCommand());
            this.put(CommandType.SHOW_RATED_ALIENS, new ShowRatedAliensCommand());
        }
    };

    /** The instance. */
    private static CommandMap instance = new CommandMap();

    /**
     * Instantiates a new command map.
     */
    private CommandMap(){}

    /**
     * Gets the single instance of CommandMap.
     *
     * @return single instance of CommandMap
     */
    public static CommandMap getInstance(){ return instance;}

    /**
     * Gets the.
     *
     * @param key the key
     * @return the command
     */
    public Command get(CommandType key){ return commandMap.get(key);}


}
