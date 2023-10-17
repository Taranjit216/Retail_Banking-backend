package retail_banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import retail_banking.entities.Account;
import retail_banking.entities.User;

public interface AccountRepo extends JpaRepository<Account, Integer>{
	
	List<Account> findByUser(User user);
}
