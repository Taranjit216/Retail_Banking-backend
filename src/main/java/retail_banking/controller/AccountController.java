package retail_banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import retail_banking.payloads.AccountDto;
import retail_banking.payloads.ApiResponse;
import retail_banking.service.AccountService;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	// create Account
	@PostMapping("/user/{userId}/account")
	public ResponseEntity<AccountDto> createAccount(
			@RequestBody AccountDto accountDto,
			@PathVariable Integer userId)
	{
		AccountDto createAccount = this.accountService.createAccount(accountDto, userId);
		return new ResponseEntity<AccountDto>(createAccount, HttpStatus.CREATED);
	}

	//get accounts by use
	@GetMapping("/user/{userId}/accounts")
	public ResponseEntity<List<AccountDto>> getAccountsByUser(@PathVariable Integer userId){
		List<AccountDto> accounts = this.accountService.getAccountByUser(userId);
		return new ResponseEntity<List<AccountDto>>(accounts, HttpStatus.OK);
	}	
	
	// getAccountByUserId
	//get single account by use
//		@GetMapping("/user/{userId}/accounts")
//		public ResponseEntity<AccountDto> getAccountsByUserId(@PathVariable Integer userId){
//			AccountDto account = this.accountService.getAccountByUserId(userId);
//			return new ResponseEntity<AccountDto>(account, HttpStatus.OK);
//		}
					
	//get all accounts
			@GetMapping("/accounts")
			public ResponseEntity<List<AccountDto>> getAccounts(){
				List<AccountDto> accounts = this.accountService.getAcounts();
				return ResponseEntity.ok(accounts);
			}
			
		// get account details by id
			@GetMapping("/accounts/{accountId}")
			public ResponseEntity<AccountDto> getPostById(@PathVariable Integer accountId){
				AccountDto accountDto = this.accountService.getAccountById(accountId);
				return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
			}
			
        //delete account
			@DeleteMapping("/accounts/{accountId}")
			public ApiResponse deletePost(@PathVariable Integer accountId) {
				
				this.accountService.deleteAccount(accountId);
				return new ApiResponse("Account is successfully deleted !!", true);
			}
	
			
          //update account
			@PutMapping("/accounts/{accountId}")
			public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto, @PathVariable Integer accountId) {
				
				AccountDto updateAccount = this.accountService.updateAccount(accountDto, accountId);
				return new ResponseEntity<AccountDto>(updateAccount, HttpStatus.OK);
			}
//			
//			//Transfer amount from one account to another
//			@PutMapping("/transfer/{accountId1}/{accountId2}")
//			public ResponseEntity<AccountDto> TransferAmount(@RequestBody AccountDto accountDto, 
//					@PathVariable Integer accountId1,
//					@PathVariable Integer accountId2) {
//				
//				AccountDto updateAccount1 = this.accountService.TransferAccount(accountDto, accountId1, accountId2);
////				AccountDto updateAccount2 = this.accountService.TransferAccount(accountDto, accountId2);
//				
////				new ResponseEntity<AccountDto>(updateAccount2, HttpStatus.OK);
//				return new ResponseEntity<AccountDto>(updateAccount1, HttpStatus.OK);
//			}
//			
//			//Transfer amount from one account to another
//			@PutMapping("/transfer/{money}/{accountId1}/{accountId2}")
//			public ResponseEntity<AccountDto> TransferMoney(@RequestBody AccountDto accountDto, 
//					@PathVariable BigDecimal money,
//					@PathVariable Integer accountId1,
//					@PathVariable Integer accountId2) {
//				
//				AccountDto updateAccount1 = this.accountService.TransferMoney(accountDto, money, accountId1, accountId2);
////				AccountDto updateAccount2 = this.accountService.TransferAccount(accountDto, accountId2);
//				
////				new ResponseEntity<AccountDto>(updateAccount2, HttpStatus.OK);
//				return new ResponseEntity<AccountDto>(updateAccount1, HttpStatus.OK);
//			}
}
