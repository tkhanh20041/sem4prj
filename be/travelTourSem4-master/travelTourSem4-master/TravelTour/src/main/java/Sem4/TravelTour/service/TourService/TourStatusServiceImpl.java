package Sem4.TravelTour.service.TourService;


import Sem4.TravelTour.entity.TourStatus;
import Sem4.TravelTour.repository.TourRepository.TourStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourStatusServiceImpl implements TourStatusService {
    @Autowired
    TourStatusRepository tourStatusRepository;

    @Override
    public TourStatus save (TourStatus tourStatus)
    {
        return tourStatusRepository.save(tourStatus);
    }
    @Override
    public boolean existsById(Long id) {
        return tourStatusRepository.existsById(id);
    }
    @Override
    public List<TourStatus> findBytourIdAndStatusFalse(Long id) {
        return tourStatusRepository.findBytourIdAndStatusFalse(id);
    }
}
