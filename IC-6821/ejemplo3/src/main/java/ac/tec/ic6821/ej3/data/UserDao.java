package ac.tec.ic6821.ej3.data;

import ac.tec.ic6821.ej3.data.support.CrudDao;
import ac.tec.ic6821.ej3.model.User;

import java.util.List;

public interface UserDao extends CrudDao<User, Long> {
    public User findByUsername(String username);
    public List<User> findAll(boolean enabled);
}
