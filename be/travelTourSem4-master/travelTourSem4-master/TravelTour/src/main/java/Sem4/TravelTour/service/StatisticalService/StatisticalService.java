package Sem4.TravelTour.service.StatisticalService;

import java.util.List;

public interface StatisticalService {
    List<Object[]> getMonthOfYear(int year);
    List<Integer> getYears();
    Double getRevenueByYear(int year);
    List<Object[]> getCategoryBestSeller();
}
