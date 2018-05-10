package ac.tec.ic6821.ej3.services.impl;

import ac.tec.ic6821.ej3.data.FileDao;
import ac.tec.ic6821.ej3.data.UserDao;
import ac.tec.ic6821.ej3.model.PersistedFile;
import ac.tec.ic6821.ej3.model.User;
import ac.tec.ic6821.ej3.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.security.Principal;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FileDao fileDao;

    /**
     * Signs up a new user into the application.
     *
     * @return User the new user instance or null if the user already exists
     */
    @Override
    public User signUp(String username, String password, String passwordConfirm) {
        if (password.equals(passwordConfirm)) {
            User existingUser = userDao.findByUsername(username);
            if (existingUser == null) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(password));

                Long id = userDao.create(user);
                return userDao.findById(id); // es incorrecto suponer que user, por efecto secundario, es la instancia persistida
            } else {
                return null;
            }
        }

        return null;
    }

    /**
     * Updates an existing user profile information
     */
    @Override
    public void updateProfile(String username, String firstName, String lastName, String email) {
        User user = userDao.findByUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userDao.update(user);
    }

    @Override
    public User getCurrentUser(Principal principal) {
        String username = ((UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUsername();
        return userDao.findByUsername(username);
    }

    @Override
    public void updateProfilePhoto(String username, String photoFilename, InputStream photo) {
        User user = userDao.findByUsername(username);

        String currentPhoto = user.getProfilePhoto();
        if (StringUtils.isNotBlank(currentPhoto)) {
            fileDao.delete(currentPhoto);
        }

        String newPhotoFilename = user.getId() + "_" + photoFilename.replace(' ', '_');
        fileDao.create(new PersistedFile(newPhotoFilename, photo));
        user.setProfilePhoto(newPhotoFilename);
        userDao.update(user);
    }

    @Override
    public InputStream getProfilePhoto(String username) {
        User user = userDao.findByUsername(username);

        String currentFilename = user.getProfilePhoto();
        if (StringUtils.isNotBlank(currentFilename)) {
            PersistedFile profilePhoto = fileDao.findById(user.getProfilePhoto());
            if (profilePhoto != null) {
                return profilePhoto.getInputStream();
            }
        }

        return null;
    }

    @Override
    public User getUser(String username) {
        return userDao.findByUsername(username);
    }
}
