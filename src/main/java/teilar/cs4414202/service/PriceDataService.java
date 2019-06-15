package teilar.cs4414202.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teilar.cs4414202.repository.dto.FuelTypeDTO;
import teilar.cs4414202.exception.ResourceNotFoundException;
import teilar.cs4414202.model.PriceData;
import teilar.cs4414202.repository.PriceDataRepository;

@Service
public class PriceDataService {
	@Autowired
	private PriceDataRepository priceDataRepository;
	
	public boolean existsById(Long productID) {
		return priceDataRepository.existsById(productID);
	}
	
	public void save(PriceData priceData) {
		priceDataRepository.save(priceData);
	}

	public List<FuelTypeDTO> getFuelTypes() {
		return priceDataRepository.getFuelTypes();
	}

	public PriceData getPriceDataById(Long productID) {
		return priceDataRepository.findById(productID).orElseThrow(()->new ResourceNotFoundException("PriceData", "productID",productID));
	}
	
}
