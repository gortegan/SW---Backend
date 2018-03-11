package startup.sw.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import startup.sw.entities.UserApp;

public interface UserService {
	public void create(UserApp user);
	public UserApp findUserByEmail(String email);
}
