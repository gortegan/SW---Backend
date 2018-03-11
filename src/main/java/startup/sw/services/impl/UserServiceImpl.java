package startup.sw.services.impl;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import startup.sw.entities.UserApp;
import startup.sw.repositories.UserRepository;
import startup.sw.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public void create(UserApp user) {
		userRepository.save(user);
	}

	@Override
	public UserApp findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserApp user = userRepository.findByEmail(email);
	if(user == null){
		throw new UsernameNotFoundException(email);
	}
    return new User(user.getEmail(), user.getPassword(), emptyList());
	}

}
