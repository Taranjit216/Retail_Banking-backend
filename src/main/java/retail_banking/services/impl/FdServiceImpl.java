package retail_banking.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import retail_banking.entities.Account;
import retail_banking.entities.FixedDeposit;
import retail_banking.exceptions.ResourceNotFoundException;
import retail_banking.payloads.FdDto;
import retail_banking.repositories.AccountRepo;
import retail_banking.repositories.FdRepo;
import retail_banking.service.FdService;

@Service
public class FdServiceImpl implements FdService{
	
	@Autowired
	private FdRepo fdRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	BigDecimal maturityAmount() {
		BigDecimal amount = BigDecimal.ZERO;
		return amount;
	}

	@Override
	public FdDto createFD(FdDto fdDto, Integer accountId) {
		Account account = this.accountRepo.findById(accountId)
				.orElseThrow( () -> new ResourceNotFoundException("Account","Account id", accountId));
		
		FixedDeposit FD = this.modelMapper.map(fdDto, FixedDeposit.class);
		FD.setAccount_type("Fixed Deposit");
		FD.setUpdated_at(new Date());
		FD.setAccount(account);
		//FD.setInterest_Rate(4.5);	
		//FD.setMaturity_amount(maturityAmount());
		
		FixedDeposit newFD = this.fdRepo.save(FD);
		
		return this.modelMapper.map(newFD, FdDto.class);
	}

	@Override
	public FdDto updateFD(FdDto fdDto, Integer FdId) {
		FixedDeposit FD = this.fdRepo.findById(FdId)
				.orElseThrow( () -> new ResourceNotFoundException("FixedDeposit","FD id", FdId));
		
		FD.setFd_amount(fdDto.getFd_amount());
		FD.setTenor(1);
		//FD.setMaturity_Date(null);
		
		FixedDeposit updateFD = this.fdRepo.save(FD);
		
		return this.modelMapper.map(updateFD, FdDto.class);
	}

	@Override
	public void deleteFD(Integer FdId) {
		FixedDeposit FD = this.fdRepo.findById(FdId)
				.orElseThrow( () -> new ResourceNotFoundException("FixedDeposit","FD id", FdId));
		
		this.fdRepo.delete(FD);
	}

	@Override
	public FdDto getFdById(Integer FdId) {
		FixedDeposit FD = this.fdRepo.findById(FdId)
				.orElseThrow( () -> new ResourceNotFoundException("FixedDeposit","FD id", FdId));
		
		return this.modelMapper.map(FD, FdDto.class);
	}

	@Override
	public List<FdDto> getFdByAccount(Integer accountId) {
		Account account = this.accountRepo.findById(accountId)
				.orElseThrow( () -> new ResourceNotFoundException("Account","Account id", accountId));
		
		List<FixedDeposit> FDs = this.fdRepo.findByAccount(account);
		
		List<FdDto> fdDtos = FDs.stream().map((FD) -> this.modelMapper.map(FD, FdDto.class)).collect(Collectors.toList());
		
		return fdDtos;
	}

	@Override
	public List<FdDto> getFds() {
		List<FixedDeposit> FDs = this.fdRepo.findAll();
		List<FdDto> fdDto = FDs.stream().
				map((FD) -> this.modelMapper.map(FD, FdDto.class)).collect(Collectors.toList());
		return fdDto;
	}

}
