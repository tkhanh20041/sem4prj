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
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private Date bookDate;
    private Double amount;
    private String address;
    private String phone;
    private int status;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
