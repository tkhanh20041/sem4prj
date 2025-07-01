package Sem4.TravelTour.service.StatisticalService;

import Sem4.TravelTour.repository.StatisticalRepository.StatisticalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private StatisticalRepository statisticalRepository;
    @Override
    public List<Object[]> getMonthOfYear(int year){
        return  statisticalRepository.getMonthOfYear(year);
    }
    @Override
    public List<Integer> getYears(){
        return statisticalRepository.getYears();
    }
    @Override
    public Double getRevenueByYear(int year){
        return statisticalRepository.getRevenueByYear(year);
    }
    @Override
    public List<Object[]> getCategoryBestSeller(){
        return statisticalRepository.getCategoryBestSeller();
    }
}
