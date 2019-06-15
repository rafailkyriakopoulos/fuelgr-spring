package teilar.cs4414202.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import teilar.cs4414202.model.GasStation;
import teilar.cs4414202.model.User;
import teilar.cs4414202.repository.dto.GeneralStatisticsDTO;

public interface GasStationRepository extends CrudRepository<GasStation, Long> {
//Λήψη δεδομένων πρατηρίων και τιμών επιλεγμένου καυσίμου.
	@Query("Select g from GasStation g JOIN FETCH g.priceData p" + " where p.fuelTypeID = ?1")
	List<GasStation> findGasStationByFuelTypeId(short fuelTypeID);
	
//Πλήθος πρατηρίων (ακέραιος), μέγιστη, ελάχιστη και μέση τιμή ανά lt (με 3 δεκαδικά).
	@Query(value="select count(gasstations.gasStationID) as allGasStations,avgPrice,minPrice,maxPrice from gasstations, \n" + 
			"(SELECT AVG(pricedata.fuelPrice) as avgPrice,min(pricedata.fuelPrice) as minPrice,max(pricedata.fuelPrice)as maxPrice from pricedata) p",
			nativeQuery=true)
	GeneralStatisticsDTO getStatistics();
	
	boolean existsByUser(User user);
	
	@Query("Select u from GasStation g JOIN  g.user u  where g.gasStationID = ?1")
	User FindUserFromGasStationID(Long gasStationId);
}
