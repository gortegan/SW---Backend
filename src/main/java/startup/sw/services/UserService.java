package startup.sw.services;

import startup.sw.entities.User;

public interface UserService {
	public void create(User user);
	public User findUserByEmail(String email);
}
