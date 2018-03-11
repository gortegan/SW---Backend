package startup.sw.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import startup.sw.entities.UserApp;

public interface UserRepository extends JpaRepository<UserApp,Long>{
	UserApp findByEmail(String email);
}
