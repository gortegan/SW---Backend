package startup.sw.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import startup.sw.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	User findByEmail(String email);
}
