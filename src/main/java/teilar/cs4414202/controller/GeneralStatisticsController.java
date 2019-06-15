package teilar.cs4414202.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import teilar.cs4414202.repository.dto.GeneralStatisticsDTO;
import teilar.cs4414202.service.GeneralStatisticsService;

@RestController
public class GeneralStatisticsController {

	@Autowired
	private GeneralStatisticsService generalStatisticsService;
	
	//Πλήθος πρατηρίων (ακέραιος), μέγιστη, ελάχιστη και μέση τιμή ανά lt
	@GetMapping("/statistics")
	public GeneralStatisticsDTO getGeneralStatistics(){
		return generalStatisticsService.getStatistics();
		
	}
}
