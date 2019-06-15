package teilar.cs4414202.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import teilar.cs4414202.model.User;
import teilar.cs4414202.repository.GasStationRepository;
import teilar.cs4414202.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GasStationRepository gasStationRepository;
	
	public boolean userExist(String username) {
		return userRepository.existsById(username);
	}
	
	public User getUserById(String username) {
		User user = userRepository.findById(username).orElseThrow( () -> new UsernameNotFoundException("Username " + username + "not found"));
		
		//Δημιουργώ χρήστη με ρόλο
		List<String> roles = new ArrayList<>(); 
		if(!gasStationRepository.existsByUser(user)) {
			roles.add("ROLE_USER");
		}
		else {
			roles.add("ROLE_OWNER");//Πρέπει να έχει πρόθεμα ROLE_ για να λειτουργήσει
			roles.add("ROLE_USER");//Πρέπει να έχει πρόθεμα ROLE_ για να λειτουργήσει
		}
		user.setRoles(roles);
		return user;
		
		
	}
}
