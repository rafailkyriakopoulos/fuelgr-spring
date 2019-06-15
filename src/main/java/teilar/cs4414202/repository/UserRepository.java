package teilar.cs4414202.repository;

import org.springframework.data.repository.CrudRepository;

import teilar.cs4414202.model.User;

public interface UserRepository extends CrudRepository<User,String>{
	

}
