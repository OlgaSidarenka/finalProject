package com.sidarenka.alien.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Homeland.
 */
public class Homeland extends Entity{
    
    /** The homeland id. */
    private long homelandId;
    
    /** The homeland name. */
    private String homelandName;

    /**
     * Instantiates a new homeland.
     */
    public Homeland() {
    }

    /**
     * Instantiates a new homeland.
     *
     * @param homelandId the homeland id
     * @param homelandName the homeland name
     */
    public Homeland(long homelandId, String homelandName) {
        this.homelandId = homelandId;
        this.homelandName = homelandName;
    }

    /**
     * Instantiates a new homeland.
     *
     * @param homelandName the homeland name
     */
    public Homeland(String homelandName) {
        this.homelandName = homelandName;
    }

    /**
     * Gets the homeland id.
     *
     * @return the homeland id
     */
    public long getHomelandId() {
        return homelandId;
    }

    /**
     * Sets the homeland id.
     *
     * @param homelandId the new homeland id
     */
    public void setHomelandId(long homelandId) {
        this.homelandId = homelandId;
    }

    /**
     * Gets the homeland name.
     *
     * @return the homeland name
     */
    public String getHomelandName() {
        return homelandName;
    }

    /**
     * Sets the homeland name.
     *
     * @param homelandName the new homeland name
     */
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

