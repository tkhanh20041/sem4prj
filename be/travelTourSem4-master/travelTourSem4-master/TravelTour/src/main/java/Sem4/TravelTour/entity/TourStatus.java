package Sem4.TravelTour.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tourstatus")
public class TourStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private Long tourId;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Boolean status;

    // Getters and Setters


    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    //toString

    @Override
    public String toString() {
        return "TourStatus{" +
                "statusId=" + statusId +
                ", tourId=" + tourId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", status=" + status +
                '}';
    }
}
