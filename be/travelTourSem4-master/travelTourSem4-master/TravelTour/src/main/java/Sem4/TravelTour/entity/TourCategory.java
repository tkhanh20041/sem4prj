package Sem4.TravelTour.entity;

import javax.persistence.*;

@Entity
@Table(name = "tour_categories")
@IdClass(TourCategoryId.class)  // Composite Key Class
public class TourCategory {

    @Id
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
