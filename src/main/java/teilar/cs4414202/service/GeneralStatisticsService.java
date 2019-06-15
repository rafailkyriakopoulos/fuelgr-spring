package teilar.cs4414202.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teilar.cs4414202.repository.GasStationRepository;
import teilar.cs4414202.repository.dto.GeneralStatisticsDTO;

@Service
public class GeneralStatisticsService {
	@Autowired
	private GasStationRepository gasStationRepository;
	
	public GeneralStatisticsDTO getStatistics(){
		return gasStationRepository.getStatistics();
	}

}
