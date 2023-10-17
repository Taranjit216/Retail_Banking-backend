package retail_banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import retail_banking.entities.Account;
import retail_banking.entities.FixedDeposit;

public interface FdRepo extends JpaRepository<FixedDeposit, Integer>{
	
	List<FixedDeposit> findByAccount(Account account);
}
