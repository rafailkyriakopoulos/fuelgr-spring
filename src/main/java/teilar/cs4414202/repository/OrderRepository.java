package teilar.cs4414202.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import teilar.cs4414202.model.Order;

public interface OrderRepository extends CrudRepository<Order,Long> {
	/*Λήψη παραγγελιών πρατηρίου*/

	/*SELECT *
		FROM orders,pricedata
	    	WHERE orders.productID = pricedata.productID
	        	AND pricedata.gasStationID = 441*/
	@Query("SELECT o FROM Order o JOIN FETCH o.priceData p where p.gasStation.gasStationID=?1")
	public List<Order> getOrders(Long gasStationID);
}
