package teilar.cs4414202.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teilar.cs4414202.model.GasStation;
import teilar.cs4414202.model.PriceData;
import teilar.cs4414202.service.GasStationService;

@RestController
@RequestMapping("/gasstations")
public class GasStationController {

	@Autowired
	private GasStationService gasStationService;

	// Λήψη δεδομένων πρατηρίων και τιμών επιλεγμένου καυσίμου.
	@GetMapping("/pricedata/{fuelTypeID}")
	public List<GasStation> getGastationByFuelTypeID(@PathVariable short fuelTypeID) {
		return gasStationService.getGasStationByFuelTypeID(fuelTypeID);
	}

	// Λήψη τιμοκαταλόγου ενός πρατηρίου
	@GetMapping("/{gasStationID}/pricelist")
	public GasStation getPriceList(@PathVariable Long gasStationID) {
		GasStation gasStation = gasStationService.findById(gasStationID);
		List<PriceData> priceData = gasStationService.getPriceList(gasStationID);
		gasStation.setPriceData(priceData);
		return gasStation;
	}
	//Λήψη gasStations
	@GetMapping("/")
	List<GasStation> getAllGasStations(){
		return gasStationService.findAll();
	}
}
