package teilar.cs4414202.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import teilar.cs4414202.model.PriceData;
import teilar.cs4414202.repository.dto.FuelTypeDTO;

public interface PriceDataRepository extends CrudRepository<PriceData, Long> {
	@Query(value="SELECT DISTINCT(`fuelNormalName`) as fuelNormalName,fuelTypeID FROM `pricedata`",
			nativeQuery=true)
	List<FuelTypeDTO> getFuelTypes();

}
