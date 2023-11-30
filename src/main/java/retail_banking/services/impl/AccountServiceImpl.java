package retail_banking.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import retail_banking.entities.Account;
import retail_banking.entities.User;
import retail_banking.exceptions.InvalidAmountException;
import retail_banking.exceptions.ResourceNotFoundException;
import retail_banking.payloads.AccountDto;
import retail_banking.payloads.UserDto;
import retail_banking.repositories.AccountRepo;
import retail_banking.repositories.UserRepo;
import retail_banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public AccountDto createAccount(AccountDto accountDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow( () -> new ResourceNotFoundException("User","User id", userId));
		
		Account account = this.modelMapper.map(accountDto, Account.class);
//		account.setAccount_number();
		account.setAccount_type("recurring");
//		account.setBalance();
		account.setUpdated_at(new Date());
		account.setUser(user);
		account.setTransferAmount(BigDecimal.ZERO);
		
		Account newAccount = this.accountRepo.save(account);
		
		return this.modelMapper.map(newAccount, AccountDto.class);
	}

	@Override
	public AccountDto updateAccount(AccountDto accountDto, Integer accountId) {
		Account account = this.accountRepo.findById(accountId)
				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId));
		
		//account.setAccount_number(accountDto.getAccount_number());
		//account.setAccount_type(accountDto.getAccount_type());
		account.setBalance(accountDto.getBalance());
		//account.setTransferAmount(BigDecimal.ZERO);
		
		Account updateAccount = this.accountRepo.save(account);
		
		return this.modelMapper.map(updateAccount, AccountDto.class);
	}

	@Override
	public void deleteAccount(Integer accountId) {
		Account account = this.accountRepo.findById(accountId)
				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId));
		
		this.accountRepo.delete(account);
	}

	@Override
	public AccountDto getAccountById(Integer accountId) {
		Account account = this.accountRepo.findById(accountId)
				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId));
		
		return this.modelMapper.map(account, AccountDto.class);
	}

	
	@Override
	public List<AccountDto> getAccountByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow( () -> new ResourceNotFoundException("User","userId", userId));
		List<Account> accounts = this.accountRepo.findByUser(user);
		
		List<AccountDto> accountDtos = accounts.stream().map((account) -> this.modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
		
		return accountDtos;
	}
	

	@Override
	public List<AccountDto> getAcounts() {
		List<Account> accounts = this.accountRepo.findAll();
		List<AccountDto> accountDto = accounts.stream().map((account) -> this.modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
		return accountDto;
	}

	@Override
	public AccountDto TransferAccount(AccountDto accountDto, Integer accountId1, Integer accountId2) {
		Account account1 = this.accountRepo.findById(accountId1)
				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId1));
		Account account2 = this.accountRepo.findById(accountId2)
				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId2));
		
		account1.setTransferAmount(account1.getTransferAmount());
		
		//account.setAccount_number(accountDto.getAccount_number());
				//account.setAccount_type(accountDto.getAccount_type());
				//account.setBalance(accountDto.getBalance());
				//account.setTransferAmount(BigDecimal.ZERO);
				
		Account update1 = this.accountRepo.save(account1);
		this.modelMapper.map(update1, AccountDto.class);
			
		BigDecimal money = account1.getTransferAmount();
		BigDecimal bal1 = account1.getBalance();
		BigDecimal bal2 = account1.getBalance();
		
		if(bal1.compareTo(money)==1) {
		BigDecimal newBal1 = bal1.subtract(money);
		BigDecimal newBal2 = bal2.add(money);
		
		account1.setBalance(newBal1);
		account2.setBalance(newBal2);
		
		}else {
			throw new InvalidAmountException();
		}
		

	    account1.setTransferAmount(BigDecimal.ZERO);
				
		Account update2 = this.accountRepo.save(account1);
		this.modelMapper.map(update2, AccountDto.class);
		
		Account updateAccount1 = this.accountRepo.save(account1);
		Account updateAccount2 = this.accountRepo.save(account2);
		
		this.modelMapper.map(updateAccount1, AccountDto.class);
		
		return this.modelMapper.map(updateAccount2, AccountDto.class);
	}
	

	@Override
	public AccountDto TransferMoney(AccountDto accountDto, BigDecimal money, Integer accountId1, Integer accountId2) {
		Account account1 = this.accountRepo.findById(accountId1)
				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId1));
		Account account2 = this.accountRepo.findById(accountId2)
				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId2));
		
		BigDecimal bal1 = account1.getBalance();
		BigDecimal bal2 = account1.getBalance();
		
		if(bal1.compareTo(money)==1) {
		BigDecimal newBal1 = bal1.subtract(money);
		BigDecimal newBal2 = bal2.add(money);
		
		account1.setBalance(newBal1);
		account2.setBalance(newBal2);
		
		}else {
			throw new InvalidAmountException();
		}
		
		
		Account updateAccount1 = this.accountRepo.save(account1);
		Account updateAccount2 = this.accountRepo.save(account2);
		
		this.modelMapper.map(updateAccount1, AccountDto.class);
		
		return this.modelMapper.map(updateAccount2, AccountDto.class);
	}

	@Override
	public AccountDto getAccountByUserId(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow( () -> new ResourceNotFoundException("User","Id", userId));
		
		return this.modelMapper.map(user, AccountDto.class);
	}
	

//	@Override
//	public AccountDto getAccountById(Integer accountId) {
//		Account account = this.accountRepo.findById(accountId)
//				.orElseThrow( () -> new ResourceNotFoundException("account","account id", accountId));
//		
//		return this.modelMapper.map(account, AccountDto.class);
//	}
//	public UserDto getUserById(Integer userId) {
//
//		User user = this.userRepo.findById(userId)
//				.orElseThrow( () -> new ResourceNotFoundException("User","Id", userId));
//		
//		return this.userToDto(user);
//	}
}
