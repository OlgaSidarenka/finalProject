package com.sidarenka.alien.entity;

/**
 * The Class User.
 */
public class User extends Entity {
    
    /** The user id. */
    private long userId;
    
    /** The login. */
    private String login;
    
    /** The password. */
    private String password;
    
    /** The email. */
    private String email;
    
    /** The user role. */
    private RoleType userRole;
    
    /** The user status. */
    private StatusType userStatus;
    
    /** The count review. */
    private int countReview;

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param login the login
     * @param password the password
     * @param email the email
     * @param userRole the user role
     * @param userStatus the user status
     */
    public User(String login, String password, String email,RoleType userRole, StatusType userStatus) {
        this.login = login;
        this.password = password;
        this.email=email;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }
    
    /**
     * Instantiates a new user.
     *
     * @param login the login
     * @param password the password
     * @param email the email
     * @param userRole the user role
     */
    public User(String login, String password,String email, RoleType userRole) {
        this.login = login;
        this.password = password;
        this.email=email;
        this.userRole = userRole;

    }
    
    /**
     * Instantiates a new user.
     *
     * @param login the login
     * @param password the password
     * @param email the email
     */
    public User(String login, String password,String email) {
        this.login = login;
        this.password = password;
        this.email=email;
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     * @param login the login
     * @param password the password
     * @param email the email
     * @param userRole the user role
     * @param userStatus the user status
     */
    public User(long userId, String login, String password, String email, RoleType userRole, StatusType userStatus) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     * @param login the login
     */
    public User(long userId, String login) {
        this.userId = userId;
        this.login = login;
    }


    /**
     * Sets the count review.
     *
     * @param countReview the new count review
     */
    public void setCountReview(int countReview) {
        this.countReview = countReview;
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
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user role.
     *
     * @return the user role
     */
    public RoleType getUserRole() {
        return userRole;
    }

    /**
     * Sets the user role.
     *
     * @param userRole the new user role
     */
    public void setUserRole(RoleType userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets the user status.
     *
     * @return the user status
     */
    public StatusType getUserStatus() {
        return userStatus;
    }

    /**
     * Sets the user status.
     *
     * @param userStatus the new user status
     */
    public void setUserStatus(StatusType userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * Gets the count review.
     *
     * @return the count review
     */
    public int getCountReview() {
        return countReview;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + (int) (userId ^ (userId >>> 32));
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (userId != other.userId)
            return false;
        if (userRole != other.userRole)
            return false;
        if (userStatus != other.userStatus)
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", userStatus=" + userStatus +
                '}';
    }
}
