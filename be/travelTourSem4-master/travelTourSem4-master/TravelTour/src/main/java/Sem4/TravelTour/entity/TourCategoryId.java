package Sem4.TravelTour.entity;

import java.io.Serializable;
import java.util.Objects;

public class TourCategoryId implements Serializable {
    private Long tour;
    private Long category;

    public TourCategoryId() {}

    public TourCategoryId(Long tour, Long category) {
        this.tour = tour;
        this.category = category;
    }

    // hashCode, equals, getters, and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourCategoryId that = (TourCategoryId) o;
        return Objects.equals(tour, that.tour) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tour, category);
    }
}
