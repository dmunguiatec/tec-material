package ac.tec.ic6821.ej3.services;

import ac.tec.ic6821.ej3.model.User;

import java.io.InputStream;
import java.security.Principal;

public interface UserService {
    User signUp(String username, String password, String passwordConfirm);
    void updateProfile(String username, String firstName, String lastName, String email);
    User getCurrentUser(Principal principal);
    void updateProfilePhoto(String username, String photoFilename, InputStream photo);
    InputStream getProfilePhoto(String username);
    User getUser(String username);
}
