package com.sidarenka.alien.entity;

public class Mark extends Entity {
    private long userId;
    private long alienId;
    private int mark;

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

    public int getMark() {
        return mark;
    }

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

    @Override
    public String toString() {
        return "Mark{" +
                "userId=" + userId +
                ", alienId=" + alienId +
                ", mark=" + mark +
                '}';
    }
}
