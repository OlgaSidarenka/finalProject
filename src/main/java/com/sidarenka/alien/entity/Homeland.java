package com.sidarenka.alien.entity;

public class Homeland extends Entity{
    private long homelandId;
    private String homelandName;

    public Homeland() {
    }

    public Homeland(long homelandId, String homelandName) {
        this.homelandId = homelandId;
        this.homelandName = homelandName;
    }

    public Homeland(String homelandName) {
        this.homelandName = homelandName;
    }

    public long getHomelandId() {
        return homelandId;
    }

    public void setHomelandId(long homelandId) {
        this.homelandId = homelandId;
    }

    public String getHomelandName() {
        return homelandName;
    }

    public void setHomelandName(String homelandName) {
        this.homelandName = homelandName;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (homelandId ^ (homelandId >>> 32));
        result = prime * result + ((homelandName == null) ? 0 : homelandName.hashCode());
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
        Homeland other = (Homeland) obj;
        if (homelandId != other.homelandId)
            return false;
        if (homelandName == null) {
            if (other.homelandName != null)
                return false;
        } else if (!homelandName.equals(other.homelandName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Homeland{" +
                "homelandId=" + homelandId +
                ", homelandName='" + homelandName + '\'' +
                '}';
    }
}

