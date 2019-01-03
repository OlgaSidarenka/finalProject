package com.sidarenka.alien.command;

import com.sidarenka.alien.command.impl.*;

import java.util.EnumMap;

public class CommandMap {
    private EnumMap<CommandType, Command> commandMap = new EnumMap<CommandType, Command>(CommandType.class){
        {
            this.put(CommandType.LOGIN, new LogInCommand());
            this.put(CommandType.LOGOUT, new LogoutCommand());
            this.put(CommandType.GOTOREGISTRATIONPAGE, new GoToRegistrationPageCommand());
            this.put(CommandType.GO_TO_MAIN_PAGE, new ErrorCommand());
            this.put(CommandType.REGISTRATION, new RegistrationCommand());
            this.put(CommandType.SEE_REVIEWS, new ShowReviewsCommand());
            this.put(CommandType.ADD_REVIEW, new AddReviewCommand());
            this.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
            this.put(CommandType.SHOW_USERS, new ShowUsersCommand());
            this.put(CommandType.SHOW_ALIENS, new ShowAliensCommand());
            this.put(CommandType.FILL_NEW_ALIEN_DATA, new FillNewAlienData());
            this.put(CommandType.ADD_ALIEN, new AddAlienCommand());
            this.put(CommandType.CHANGE_USER_STATUS, new ChangeUserStatusCommand());
            this.put(CommandType.RATE_ALIEN, new RateAlienCommand());
            this.put(CommandType.SHOW_ALIEN_BY_NAME, new ShowAlienByNameCommand());
            this.put(CommandType.UPDATE_ALIEN, new UpdateAlienCommand());
        }
    };

    private static CommandMap instance = new CommandMap();

    private CommandMap(){}

    public static CommandMap getInstance(){ return instance;}

    public Command get(CommandType key){ return commandMap.get(key);}


}
