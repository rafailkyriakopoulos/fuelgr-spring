package teilar.cs4414202.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teilar.cs4414202.exception.ForbiddenException;
import teilar.cs4414202.exception.ResourceNotFoundException;
import teilar.cs4414202.model.Order;
import teilar.cs4414202.model.User;
import teilar.cs4414202.service.GasStationService;
import teilar.cs4414202.service.OrderService;



@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired  
	private GasStationService gasStationService;
	
	//Αποστολή δεδομένων παραγγελίας από πλευράς χρήστη (ποσότητα)
	@PostMapping("")
	@Secured("ROLE_USER")
	public String addOrder(@RequestBody Order order,Principal prinsipal) {
		
		//Βάζουμε τον λογκαρισμένο χρήστη στην παραγγελία 
		User user = new User();
		user.setUsername(prinsipal.getName());
		
		//Αποθηκεύουμε την παραγγελία στην db
		order.setUser(user);
		
		Long orderId = orderService.addOrder(order);
		return "{ \"message\":\"Your order has been stored\","
				+"\"username\":\""+user.getUsername()+"\","
				+"\"orderID\":\""+orderId+"\""+"}";
	}
	
	//Λήψη παραγγελιών πρατηρίου
	@Secured("ROLE_OWNER")
	@GetMapping("/{gasStationID}")
	public List<Order> getOrders(@PathVariable Long gasStationID,Principal principal){
		if(!gasStationService.getUserFromGasStationID(gasStationID).getUsername().equals(principal.getName())){
			throw new ForbiddenException("you are  not the owner of that gasstation "+gasStationID);
		}
		return orderService.getOrders(gasStationID);
	}
	
	//DELETE: Διαγραφή παραγγελίας από πρατηριούχο
	@DeleteMapping("/{orderID}")
	@Secured("ROLE_OWNER")
	public String deleteOrder(@PathVariable Long orderID,Principal prinsipal) {
		if(!orderService.existById(orderID)) {
			throw new ResourceNotFoundException("orders", "orderID", orderID);
		}
		
		Order order = orderService.findOrderById(orderID);
		
		if(!order.getUser().getUsername().equals(prinsipal.getName())) {
			throw new ForbiddenException("You can not delete the order because you are not the owner of order id  "+orderID);
		}
		
		orderService.deleteOrder(order);
		
		return "{ \"message\":\"Your order has been deleted\","
				+"\"orderID\":\""+orderID+"\""+"}";
	}
}
