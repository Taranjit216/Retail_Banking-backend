package retail_banking.service;


import java.util.List;

import jakarta.validation.Valid;
import retail_banking.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers(); 
	void DeleteUser(Integer userId);
	UserDto registerNewUser(@Valid UserDto userDto);

} 
