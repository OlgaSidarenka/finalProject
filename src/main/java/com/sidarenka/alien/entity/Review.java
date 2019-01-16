package com.sidarenka.alien.entity;

import java.sql.Date;

// TODO: Auto-generated Javadoc

/**
 * The Class Review.
 */
public class Review extends Entity {

    /**
     * The review id.
     */
    private long reviewId;

    /**
     * The user id.
     */
    private long userId;

    /**
     * The alien id.
     */
    private long alienId;

    /**
     * The login.
     */
    private String login;

    /**
     * The alien name.
     */
    private String alienName;

    /**
     * The text review.
     */
    private String textReview;

    /**
     * The date review.
     */
    private Date dateReview;

    /**
     * The unblocked.
     */
    private boolean unblocked;

    /**
     * Instantiates a new review.
     */
    public Review() {
        this.unblocked = true;
    }

    /**
     * Checks if is unblocked.
     *
     * @return true, if is unblocked
     */
    public boolean isUnblocked() {
        return unblocked;
    }

    /**
     * Sets the unblocked.
     *
     * @param unblocked the new unblocked
     */
    public void setUnblocked(boolean unblocked) {
        this.unblocked = unblocked;
    }

    /**
     * Sets the review id.
     *
     * @param reviewId the new review id
     */
    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the alien id.
     *
     * @return the alien id
     */
    public long getAlienId() {
        return alienId;
    }

    /**
     * Sets the alien id.
     *
     * @param alienId the new alien id
     */
    public void setAlienId(long alienId) {
        this.alienId = alienId;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the alien name.
     *
     * @return the alien name
     */
    public String getAlienName() {
        return alienName;
    }

    /**
     * Sets the alien name.
     *
     * @param alienName the new alien name
     */
    public void setAlienName(String alienName) {
        this.alienName = alienName;
    }

    /**
     * Gets the text review.
     *
     * @return the text review
     */
    public String getTextReview() {
        return textReview;
    }

    /**
     * Sets the text review.
     *
     * @param textReview the new text review
     */
    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    /**
     * Gets the date review.
     *
     * @return the date review
     */
    public Date getDateReview() {
        return dateReview;
    }

    /**
     * Sets the date review.
     *
     * @param dateReview the new date review
     */
    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    /**
     * Gets the review id.
     *
     * @return the review id
     */
    public long getReviewId() {
        return reviewId;
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
