package teilar.cs4414202.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teilar.cs4414202.exception.ResourceNotFoundException;
import teilar.cs4414202.model.GasStation;
import teilar.cs4414202.model.PriceData;
import teilar.cs4414202.model.User;
import teilar.cs4414202.repository.GasStationRepository;



@Service
public class GasStationService {
	@Autowired
	private GasStationRepository gasStationRepository;
	
	//Λήψη δεδομένων πρατηρίων και τιμών επιλεγμένου καυσίμου
	public List<GasStation> getGasStationByFuelTypeID(short fuelTypeID){
		return gasStationRepository.findGasStationByFuelTypeId(fuelTypeID);
	}

	//Λήψη τιμοκαταλόγου ενός πρατηρίου.
	public List<PriceData> getPriceList(Long gasStationID) {
		if (!gasStationRepository.existsById(gasStationID)) {
			throw new ResourceNotFoundException("gasstation", "gasStationID", gasStationID);
		}
		return gasStationRepository.findById(gasStationID).get().getPriceData();
	}
	
	//Λήψη χρήστη από gasStation
	public User getUserFromGasStationID(Long gasStationID) {
		return gasStationRepository.FindUserFromGasStationID(gasStationID);
	}

	public GasStation findById(Long gasStationID) {
		return gasStationRepository.findById(gasStationID).orElseThrow(()->new ResourceNotFoundException("gasStations", "gasStationID", gasStationID));
	}

	public List<GasStation> findAll() {
		List<GasStation> gasStations = new ArrayList<>();
		/*Βάζω null τιμές τα παρακάτω πεδία ώστε να μην τα κάνει parse ο jackson*/
		gasStationRepository.findAll().forEach((g)-> {
			g.setPriceData(null);
			g.setUsername(null);
			gasStations.add(g);
		});
		return gasStations;
	}

}
