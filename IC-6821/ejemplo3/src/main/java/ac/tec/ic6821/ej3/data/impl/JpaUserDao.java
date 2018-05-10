package ac.tec.ic6821.ej3.data.impl;

import ac.tec.ic6821.ej3.data.UserDao;
import ac.tec.ic6821.ej3.data.jpa.UserJpaRepository;
import ac.tec.ic6821.ej3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class JpaUserDao implements UserDao {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll(boolean enabled) {
        return userJpaRepository.findAll(enabled);
    }

    @Override
    public Long create(User entity) {
        User persistedEntity = userJpaRepository.save(entity);
        return persistedEntity.getId();
    }

    @Override
    public User findById(Long id) {
        return userJpaRepository.findOne(id);
    }

    @Override
    public void update(User entity) {
        userJpaRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userJpaRepository.delete(id);
    }
}
