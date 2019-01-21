package com.sidarenka.alien.dao;

public class SqlQuery {
    //User
    public static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";
    public static final String SQL_INSERT_USER = "INSERT INTO user(login,password,email,roleId,statusId) VALUES(?,?,?,?,?)";
    public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM user WHERE login =?";
    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE userId =?";
    public static final String SQL_SELECT_AII_USERS = "SELECT user.userId,user.login, user.email, user.password, " +
            "user.statusId, user.roleId, COUNT(reviews.userId) FROM user LEFT JOIN reviews" +
            " on  user.userId=reviews.userId group by user.userId having user.roleId=2";
    public static final String SQL_UPDATE_USER_STATUS = "UPDATE user SET statusId=? WHERE login=?";
    public static final String SQL_SELECT_USER_REVIEWS = "SELECT reviews.alienId, alien.alienName, user.login," +
            " reviews.textReview,reviews.dateReview, reviews.reviewId, reviews.userId, reviews.unblocked FROM reviews" +
            " LEFT JOIN user on user.userId=reviews.userId LEFT JOIN alien on reviews.alienId=alien.alienId where reviews.userId=?";
    public static final String SQL_BLOCK_USER_REVIEW = "UPDATE reviews SET unblocked=false WHERE reviewId=?";
    //Alien
    public static final String SQL_SELECT_AII_ALIENS = "SELECT alien.alienName, homeland.homelandId, homeland.homelandName," +
            "alien.alienDescription, ROUND(AVG(marks.mark),2), alien.alienId  FROM alien  LEFT JOIN marks " +
            "ON alien.alienId=marks.alienId LEFT JOIN homeland ON alien.homelandId=homeland.homelandId GROUP BY alien.alienId " +
            "ORDER BY ROUND(AVG(marks.mark),2) DESC";
    public static final String SQL_SELECT_ALIEN_REVIEWS = "SELECT reviews.alienId, alien.alienName, user.login," +
            " reviews.textReview,reviews.dateReview FROM reviews left join user on user.userId=reviews.userId" +
            " left join alien on reviews.alienId=alien.alienId where reviews.alienId=? AND reviews.unblocked=true";
    public static final String SQL_SELECT_RATED_ALIENS_FOR_USER=" SELECT alien.alienName, homeland.homelandId, marks.userId, homeland.homelandName,alien.alienDescription," +
            "ROUND(AVG(marks.mark),2),alien.alienId  FROM alien  LEFT JOIN marks ON alien.alienId=marks.alienId LEFT JOIN homeland ON\n" +
            "  alien.homelandId=homeland.homelandId GROUP BY alien.alienId  HAVING marks.userId=? ORDER BY ROUND(AVG(marks.mark),2) DESC";
    public static final String SQL_INSERT_REVIEW = "INSERT INTO reviews(alienId, userId, textReview,dateReview, unblocked) VALUES(?,?,?,?,?)";
    public static final String SQL_FIND_ALIEN_BY_ID = "SELECT * FROM alien WHERE alienId =?";
    public static final String SQL_INSERT_ALIEN = "INSERT INTO alien (alienName,alienDescription,homelandId) VALUES(?,?,?)";
    public static final String SQL_TAKE_ALIEN_INFORMATION_BY_NAME = "SELECT alien.alienId,alien.alienName," +
            " homeland.homelandId, homeland.homelandName, alien.alienDescription, ROUND(AVG(marks.mark),2) " +
            "FROM alien  LEFT JOIN marks ON alien.alienId=marks.alienId LEFT JOIN homeland " +
            "ON alien.homelandId=homeland.homelandId WHERE alienName LIKE ? GROUP BY alien.alienName";
    public static final String SQL_FIND_ALIEN_BY_NAME = "SELECT * FROM alien WHERE alienName=?";
    public static final String SQL_FIND_ALIEN_MARK_FROM_USER = "SELECT mark FROM marks WHERE userId=? AND alienId=?";
    public static final String SQL_INSERT_ALIEN_MARK = "INSERT INTO marks (userId,alienId,mark) VALUES(?,?,?)";
    public static final String SQL_UPDATE_ALIEN_DESCRIPTION = "UPDATE alien SET alienDescription=? where alienName=?";
    //Homeland
    public static final String SQL_FIND_HOMELAND_ID_BY_HOMELAND_NAME = "SELECT homelandId FROM homeland WHERE homelandName=?";
    public static final String SQL_INSERT_HOMELAND = "INSERT INTO homeland(homelandName) VALUES(?)";
    public static final String SQL_FIND_ALL_HOMELANDS = "SELECT * FROM homeland";

    private SqlQuery() {
    }
}
