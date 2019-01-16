package com.sidarenka.alien.entity;

public class Alien extends Entity {
    /**
     * The alien id.
     */
    private long alienId;
    /**
     * The alien name.
     */
    private String alienName;
    /**
     * The alien description.
     */
    private String description;
    /**
     * The alien homeland.
     */
    private Homeland homeland;
    /**
     * The average mark.
     */
    private double averageMark;

    /**
     * Instantiates a new alien.
     */
    public Alien() {
    }

    /**
     * Instantiates a new alien.
     *
     * @param alienId     the alien id
     * @param alienName   the alien name
     * @param description the description
     * @param homeland    the homeland
     */
    public Alien(long alienId, String alienName, String description, Homeland homeland) {
        this.alienId = alienId;
        this.alienName = alienName;
        this.description = description;
        this.homeland = homeland;
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
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the homeland.
     *
     * @return the homeland
     */
    public Homeland getHomeland() {
        return homeland;
    }

    /**
     * Sets the homeland.
     *
     * @param homeland the new homeland
     */
    public void setHomeland(Homeland homeland) {
        this.homeland = homeland;
    }

    /**
     * Gets the average mark.
     *
     * @return the average mark
     */
    public double getAverageMark() {
        return averageMark;
    }

    /**
     * Sets the average mark.
     *
     * @param averageMark the new average mark
     */
    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (alienId ^ (alienId >>> 32));
        result = prime * result + ((alienName == null) ? 0 : alienName.hashCode());
        long temp;
        temp = Double.doubleToLongBits(averageMark);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((homeland == null) ? 0 : homeland.hashCode());
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
        Alien other = (Alien) obj;
        if (alienId != other.alienId)
            return false;
        if (alienName == null) {
            if (other.alienName != null)
                return false;
        } else if (!alienName.equals(other.alienName))
            return false;
        if (Double.doubleToLongBits(averageMark) != Double.doubleToLongBits(other.averageMark))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (homeland == null) {
            if (other.homeland != null)
                return false;
        } else if (!homeland.equals(other.homeland))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "alienId=" + alienId +
                ", alienName='" + alienName + '\'' +
                ", description='" + description + '\'' +
                ", homeland=" + homeland +
                ", averageMark=" + averageMark +
                '}';
    }
}
