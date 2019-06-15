package teilar.cs4414202.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import teilar.cs4414202.exception.ForbiddenException;
import teilar.cs4414202.exception.ResourceNotFoundException;
import teilar.cs4414202.model.PriceData;
import teilar.cs4414202.model.User;
import teilar.cs4414202.repository.dto.FuelTypeDTO;
import teilar.cs4414202.service.GasStationService;
import teilar.cs4414202.service.PriceDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class PriceDataController {

	@Autowired
	private PriceDataService priceDataService;
	
	@Autowired
	private GasStationService gasStationService;
	
	//Αλλαγή τιμής σε καύσιμο
	@PutMapping("/pricedata/{productID}")
	@Secured("ROLE_OWNER")
	public String updatePriceData(@PathVariable Long productID,@RequestBody PriceData priceData,Principal principal) {
		if(!priceDataService.existsById(productID)) {
			throw new ResourceNotFoundException("pricedata", "productID", productID);
		}
		
		PriceData priceDataFromDb = priceDataService.getPriceDataById(productID);
		if(!priceDataFromDb.getGasStation().getUser().getUsername().equals(principal.getName())){
			throw new ForbiddenException("you are not the owner of the product");
		}
		
	
		priceDataFromDb.setFuelName(priceData.getFuelName());
		priceDataFromDb.setFuelNormalName(priceData.getFuelNormalName());
		priceDataFromDb.setFuelPrice(priceData.getFuelPrice());
		priceDataService.save(priceDataFromDb);
		return "{ \"message\":\"Your product has been updated\","
				+"\"productID\":\""+productID+"\""+"}";
	}
	//Επιστροφή fuelNormalName,fuelTypeID
	@GetMapping("/priceData/fuelTypes")
	public List<FuelTypeDTO> getFuelTypes(){
		return priceDataService.getFuelTypes();
	}
	@GetMapping("/priceData/{productID}")
	public PriceData getPriceData(@PathVariable Long productID) {
		return priceDataService.getPriceDataById(productID);
	}

	
}
