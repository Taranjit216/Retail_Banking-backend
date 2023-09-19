package retail_banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import retail_banking.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	
	
}
