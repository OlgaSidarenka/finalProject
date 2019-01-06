package com.sidarenka.alien.dao;

public class SqlQuery {
    //User
    public static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";
    public static final String SQL_INSERT_USER = "INSERT INTO user(login,password,email,roleId,statusId) VALUES(?,?,?,?,?)";
    public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM user WHERE login =?";
    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE userId =?";
    public static final String SQL_SELECT_AII_USERS = "SELECT * FROM user WHERE roleId=2";
    public static final String SQL_UPDATE_USER_STATUS="UPDATE user SET statusId=? WHERE login=?";
    public static final String SQL_SELECT_USER_REVIEWS = "SELECT reviews.alienId, alien.alienName, user.login," +
            " reviews.textReview,reviews.dateReview FROM reviews left join user on user.userId=reviews.userId" +
            " left join alien on reviews.alienId=alien.alienId where reviews.userId=?";
    //Alien
    public static final String SQL_SELECT_AII_ALIENS = "SELECT alien.alienName, homeland.homelandId, homeland.homelandName," +
            "alien.alienDescription,AVG(marks.mark), alien.alienId  FROM alien  LEFT JOIN marks " +
            "ON alien.alienId=marks.alienId LEFT JOIN homeland ON alien.homelandId=homeland.homelandId GROUP BY alien.alienId";
    public static final String SQL_SELECT_ALIEN_REVIEWS = "SELECT reviews.alienId, alien.alienName, user.login," +
            " reviews.textReview,reviews.dateReview FROM reviews left join user on user.userId=reviews.userId" +
            " left join alien on reviews.alienId=alien.alienId where reviews.alienId=?";
    public static final String SQL_INSERT_REVIEW = "INSERT INTO reviews(alienId, userId, textReview,dateReview) VALUES(?,?,?,?)";
    public static final String SQL_FIND_ALIEN_BY_ID = "SELECT * FROM alien WHERE alienId =?";
    public static final String SQL_DELETE_ALIEN_BY_ID = "DELETE FROM alien WHERE alienId =?";
    public static final String SQL_INSERT_ALIEN = "INSERT INTO alien (alienName,alienDescription,homelandId) VALUES(?,?,?)";
    public static final String SQL_TAKE_ALIEN_INFORMATION_BY_NAME = "SELECT alien.alienId,alien.alienName," +
            " homeland.homelandId, homeland.homelandName, alien.alienDescription,AVG(marks.mark) " +
            "FROM alien  LEFT JOIN marks ON alien.alienId=marks.alienId LEFT JOIN homeland " +
            "ON alien.homelandId=homeland.homelandId WHERE alienName LIKE ? GROUP BY alien.alienName";
    public static final String SQL_FIND_ALIEN_BY_NAME = "SELECT * FROM alien WHERE alienName=?";
    public static final String SQL_FIND_ALIEN_MARK_FROM_USER = "SELECT mark FROM marks WHERE userId=? AND alienId=?";
    public static final String SQL_INSERT_ALIEN_MARK = "INSERT INTO marks (userId,alienId,mark) VALUES(?,?,?)";
    public static final String SQL_UPDATE_ALIEN_DESCRIPTION="UPDATE alien SET alienDescription=? where alienName=?";
    //Homeland
    public static final String SQL_FIND_HOMELAND_ID_BY_HOMELAND_NAME = "SELECT homelandId FROM homeland WHERE homelandName=?";
    public static final String SQL_INSERT_HOMELAND="INSERT INTO homeland(homelandName) VALUES(?)";
}
