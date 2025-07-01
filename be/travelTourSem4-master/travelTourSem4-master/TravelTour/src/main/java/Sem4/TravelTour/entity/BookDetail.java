package Sem4.TravelTour.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookDetails")
public class BookDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookDetailId;
    private int quantity;
    private Double price;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "tourId")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

}
