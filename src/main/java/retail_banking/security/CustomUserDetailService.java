package retail_banking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import retail_banking.entities.User;
import retail_banking.exceptions.ResourceNotFoundException;
import retail_banking.repositories.UserRepo;



@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from database by user-name
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User ", "email : "+username, 0));
		
		return user;
	}

}
