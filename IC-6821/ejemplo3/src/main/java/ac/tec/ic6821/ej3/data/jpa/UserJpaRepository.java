package ac.tec.ic6821.ej3.data.jpa;

import ac.tec.ic6821.ej3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    @Query("from User u where u.username = ?1")
    User findByUsername(String username);

    @Query("from User u where u.enabled = ?1")
    List<User> findAll(boolean enabled);
}
