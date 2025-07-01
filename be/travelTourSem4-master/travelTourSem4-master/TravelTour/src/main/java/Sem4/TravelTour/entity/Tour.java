package Sem4.TravelTour.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tours")
public class Tour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Long tourId;
    private String name;
    private int quantity;
    private Double price;
    private int discount;
    private String image;
    private String description;
    private LocalDate enteredDate;
    private Boolean status;
    private int duration;
    private int sold;
    @Column(name = "time_id")
    private int timeId;

    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;

    @ManyToMany
    @JoinTable(
    name = "tour_categories",
    joinColumns = @JoinColumn(name = "tour_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

//    @OneToMany(mappedBy = "tour")
//    private Set<Location> locations;
//
//    @OneToMany(mappedBy = "tour")
//    private Set<TourStatus> tourStatuses;
//
//    @OneToMany(mappedBy = "tour")
//    private Set<BookDetail> bookDetails;
//
//    @OneToMany(mappedBy = "tour")
//    private Set<CartDetail> cartDetails;
//
//    @OneToMany(mappedBy = "tour")
//    private Set<Favorite> favorites;
//
//    @OneToMany(mappedBy = "tour")
//    private Set<Rate> rates;

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", enteredDate=" + enteredDate +
                ", status=" + status +
                ", duration=" + duration +
                ", sold=" + sold +
                ", timeId=" + timeId +
                ", category=" + category +
                ", location=" + location +
                '}';
    }


    //    @Override
//    public String toString() {
//        return "Tour{" +
//                "tourId=" + tourId +
//                ", name='" + name + '\'' +
//                ", quantity=" + quantity +
//                ", price=" + price +
//                ", discount=" + discount +
//                ", image='" + image + '\'' +
//                ", description='" + description + '\'' +
//                ", enteredDate=" + enteredDate +
//                ", status=" + status +
//                ", duration=" + duration +
//                ", sold=" + sold +
//                ", category=" + category +
//                '}';
//    }
}
