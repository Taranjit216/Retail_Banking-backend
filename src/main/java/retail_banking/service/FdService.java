package retail_banking.service;

import java.util.List;
import retail_banking.payloads.FdDto;

public interface FdService {

	//create
	FdDto createFD(FdDto fdDto, Integer accountId);
	
	//update
	FdDto updateFD(FdDto fdDto, Integer FdId);
	
	//delete
	void deleteFD(Integer FdId);
	
	//get FD
	FdDto getFdById(Integer FdId);
	
	//get all accounts by user
	List<FdDto> getFdByAccount(Integer accountId);
	
	//get All
	List<FdDto> getFds();
	
}

