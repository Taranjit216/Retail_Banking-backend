package retail_banking.service;

import java.math.BigDecimal;
import java.util.List;

import retail_banking.payloads.AccountDto;

public interface AccountService {

	//create
	AccountDto createAccount(AccountDto accountDto, Integer userId);
	
	//update
	AccountDto updateAccount(AccountDto accountDto, Integer accountId);
	
	//transfer to another account
	AccountDto TransferAccount(AccountDto accountDto, Integer accountId1, Integer accountId2);
	
	//transfer to another account
	AccountDto TransferMoney(AccountDto accountDto, BigDecimal money, Integer accountId1, Integer accountId2);
	
	//delete
	void deleteAccount(Integer accountId);
	
	//get account
	AccountDto getAccountById(Integer accountId);
	
	//get all accounts by user
	List<AccountDto> getAccountByUser(Integer userId);
	
	//get All
	List<AccountDto> getAcounts();
	
}
