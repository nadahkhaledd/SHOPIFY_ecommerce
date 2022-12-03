package org.example.model;

public class Star {
    public int numberOfEmptyStars;
    public boolean hasHalfStar;
    public int numberOfFullStars;

    public int getNumberOfEmptyStars() {
        return numberOfEmptyStars;
    }

    public void setNumberOfEmptyStars(int numberOfEmptyStars) {
        this.numberOfEmptyStars = numberOfEmptyStars;
    }

    public boolean isHasHalfStar() {
        return hasHalfStar;
    }

    public void setHasHalfStar(boolean hasHalfStar) {
        this.hasHalfStar = hasHalfStar;
    }

    public int getNumberOfFullStars() {
        return numberOfFullStars;
    }

    public void setNumberOfFullStars(int numberOfFullStars) {
        this.numberOfFullStars = numberOfFullStars;
    }

    @Override
    public String toString() {
        return "Star{" +
                "numberOfEmptyStars=" + numberOfEmptyStars +
                ", hasHalfStar=" + hasHalfStar +
                ", numberOfFullStars=" + numberOfFullStars +
                '}';
    }
}
