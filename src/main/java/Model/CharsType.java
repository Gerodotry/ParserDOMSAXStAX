package Model;

import java.util.List;
import java.util.Objects;

public class CharsType {
    private boolean color;
    private int numberOfPages;
    private boolean glossy;
    private boolean hasSubscriptionIndex;
    public CharsType() {
        // Default values or leave them uninitialized
    }
    public CharsType(boolean color, int numberOfPages, boolean glossy, boolean hasSubscriptionIndex) {
        this.color = color;
        this.numberOfPages = numberOfPages;
        this.glossy = glossy;
        this.hasSubscriptionIndex = hasSubscriptionIndex;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public boolean isGlossy() {
        return glossy;
    }

    public void setGlossy(boolean glossy) {
        this.glossy = glossy;
    }

    public boolean isHasSubscriptionIndex() {
        return hasSubscriptionIndex;
    }

    public void setHasSubscriptionIndex(boolean hasSubscriptionIndex) {
        this.hasSubscriptionIndex = hasSubscriptionIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharsType)) return false;
        CharsType charsType = (CharsType) o;
        return color == charsType.color &&
                numberOfPages == charsType.numberOfPages &&
                glossy == charsType.glossy &&
                hasSubscriptionIndex == charsType.hasSubscriptionIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, numberOfPages, glossy, hasSubscriptionIndex);
    }

    @Override
    public String toString() {
        return "CharsType{" +
                "color=" + color +
                ", numberOfPages=" + numberOfPages +
                ", glossy=" + glossy +
                ", hasSubscriptionIndex=" + hasSubscriptionIndex +
                '}';
    }
}