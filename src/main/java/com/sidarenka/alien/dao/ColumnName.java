package com.sidarenka.alien.dao;

public class ColumnName {
    public static String USER_ID = "userId";
    public static String USER_LOGIN = "login";
    public static String PASSWORD = "password";
    public static String EMAIL = "email";
    public static String ROLE_ID = "roleId";
    public static String COUNT_REVIEW="COUNT(reviews.userId)";
    public static String STATUS_ID = "statusId";

    public static String ALIEN_ID = "alienId";
    public static String ALIEN_NAME = "alienName";
    public static String ALIEN_DESCRIPTION = "alienDescription";
    public static String HOMELAND_ID = "homelandId";
    public static String HOMELAND_NAME = "homelandName";
    public static String AVERAGE_MARK="ROUND(AVG(marks.mark),2)";

    public static String TEXT_REVIEW = "textReview";
    public static String DATE_REVIEW = "dateReview";
    public static String REVIEW_ID="reviewId";
    public static String REVIEW_IS_UNBLOCKED="unblocked";

}
