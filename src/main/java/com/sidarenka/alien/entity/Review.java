package com.sidarenka.alien.entity;
import java.sql.Date;

public class Review extends Entity {
    private long userId;
    private long alienId;
    private String login;
    private String alienName;
    private String textReview;
    private Date dateReview;

    public Review() {
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAlienId() {
        return alienId;
    }

    public void setAlienId(long alienId) {
        this.alienId = alienId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAlienName() {
        return alienName;
    }

    public void setAlienName(String alienName) {
        this.alienName = alienName;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        if (alienId != other.alienId)
            return false;
        if (alienName == null) {
            if (other.alienName != null)
                return false;
        } else if (!alienName.equals(other.alienName))
            return false;
        if (dateReview == null) {
            if (other.dateReview != null)
                return false;
        } else if (!dateReview.equals(other.dateReview))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (textReview == null) {
            if (other.textReview != null)
                return false;
        } else if (!textReview.equals(other.textReview))
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userId=" + userId +
                ", alienId=" + alienId +
                ", login='" + login + '\'' +
                ", alienName='" + alienName + '\'' +
                ", textReview='" + textReview + '\'' +
                ", dateReview=" + dateReview +
                '}';
    }
}
