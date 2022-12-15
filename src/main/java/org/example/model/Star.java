package org.example.model;

import java.util.Objects;

public class Star {
    private int numberOfEmptyStars;
    private boolean hasHalfStar;
    private int numberOfFullStars;

    public Star(){}
    public Star(int numberOfEmptyStars, boolean hasHalfStar, int numberOfFullStars) {
        this.numberOfEmptyStars = numberOfEmptyStars;
        this.hasHalfStar = hasHalfStar;
        this.numberOfFullStars = numberOfFullStars;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return numberOfEmptyStars == star.numberOfEmptyStars && hasHalfStar == star.hasHalfStar && numberOfFullStars == star.numberOfFullStars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfEmptyStars, hasHalfStar, numberOfFullStars);
    }
}
