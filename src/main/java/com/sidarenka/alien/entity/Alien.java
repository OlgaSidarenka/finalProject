package com.sidarenka.alien.entity;

public class Alien extends Entity {
    private long alienId;
    private String alienName;
    private String description;

    private String image

            ;
    private Homeland homeland;
    private double averageMark;

    public Alien() {
    }

    public Alien(long alienId, String alienName, String description, Homeland homeland) {
        this.alienId = alienId;
        this.alienName = alienName;
        this.description = description;
        this.homeland = homeland;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getAlienId() {
        return alienId;
    }

    public void setAlienId(long alienId) {
        this.alienId = alienId;
    }

    public String getAlienName() {
        return alienName;
    }

    public void setAlienName(String alienName) {
        this.alienName = alienName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Homeland getHomeland() {
        return homeland;
    }

    public void setHomeland(Homeland homeland) {
        this.homeland = homeland;
    }

    public double getAverageMark() {
        return averageMark;
    }

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
