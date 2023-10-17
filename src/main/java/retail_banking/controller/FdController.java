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

import retail_banking.payloads.ApiResponse;
import retail_banking.payloads.FdDto;
import retail_banking.service.FdService;

@RestController
@RequestMapping("/api/v1/")
public class FdController {
	
	@Autowired
	private FdService fdService;
	
	// create FD
	@PostMapping("/account/{accountId}/fds")
	public ResponseEntity<FdDto> createAccount(
			@RequestBody FdDto fdDto,
			@PathVariable Integer accountId)
	{
		FdDto createFd = this.fdService.createFD(fdDto, accountId);
		return new ResponseEntity<FdDto>(createFd, HttpStatus.CREATED);
	}

	//get FDs by account
	@GetMapping("/account/{accountId}/fds")
	public ResponseEntity<List<FdDto>> getFdByAccounts(@PathVariable Integer accountId){
		List<FdDto> FDs = this.fdService.getFdByAccount(accountId);
		
		return new ResponseEntity<List<FdDto>>(FDs, HttpStatus.OK);
	}	
					
	//get all fds
			@GetMapping("/fds")
			public ResponseEntity<List<FdDto>> getFds(){
				List<FdDto> accounts = this.fdService.getFds();
				return ResponseEntity.ok(accounts);
			}
			
		// get fd details by id
			@GetMapping("/fds/{fdId}")
			public ResponseEntity<FdDto> getFdById(@PathVariable Integer fdId){
				FdDto fdDto = this.fdService.getFdById(fdId);
				return new ResponseEntity<FdDto>(fdDto, HttpStatus.OK);
			}
			
        //delete account
			@DeleteMapping("/fds/{fdId}")
			public ApiResponse deleteFd(@PathVariable Integer fdId) {
				
				this.fdService.deleteFD(fdId);
				return new ApiResponse("Fd is successfully deleted !!", true);
			}
	
			
          //update account
			@PutMapping("/fds/{fdId}")
			public ResponseEntity<FdDto> updateFd(@RequestBody FdDto fdDto, @PathVariable Integer fdId) {
				
				FdDto updatefd = this.fdService.updateFD(fdDto, fdId);
				return new ResponseEntity<FdDto>(updatefd, HttpStatus.OK);
			}
}

