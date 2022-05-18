package edu.tec.ic6821.springjdbc.users;

import edu.tec.ic6821.springjdbc.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    List<User> findByName(String name);

}
