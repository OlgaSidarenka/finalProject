package com.sidarenka.alien.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Mark.
 */
public class Mark extends Entity {
    
    /** The user id. */
    private long userId;
    
    /** The alien id. */
    private long alienId;
    
    /** The mark. */
    private int mark;

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
     * Gets the mark.
     *
     * @return the mark
     */
    public int getMark() {
        return mark;
    }

    /**
     * Sets the mark.
     *
     * @param mark the new mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (alienId ^ (alienId >>> 32));
        result = prime * result + mark;
        result = prime * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mark other = (Mark) obj;
        if (alienId != other.alienId)
            return false;
        if (mark != other.mark)
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Mark{" +
                "userId=" + userId +
                ", alienId=" + alienId +
                ", mark=" + System.out.format("%10.3f",mark) +
                '}';
    }
}
