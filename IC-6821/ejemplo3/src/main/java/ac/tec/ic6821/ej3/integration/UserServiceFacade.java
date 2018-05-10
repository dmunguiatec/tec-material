package ac.tec.ic6821.ej3.integration;

import ac.tec.ic6821.ej3.integration.support.RestUtils;
import ac.tec.ic6821.ej3.model.User;
import ac.tec.ic6821.ej3.model.vo.CredentialsVO;
import ac.tec.ic6821.ej3.model.vo.ProfilePhotoVO;
import ac.tec.ic6821.ej3.model.vo.ProfileVO;
import ac.tec.ic6821.ej3.services.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserServiceFacade {

    private static final Logger log = LoggerFactory.getLogger(UserServiceFacade.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public @ResponseBody User signUp(@RequestBody CredentialsVO credentials) {
        return userService.signUp(
                credentials.getUsername(),
                credentials.getPassword(),
                credentials.getConfirmPassword());
    }

    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public @ResponseBody User profile(@RequestBody ProfileVO profile) {
        userService.updateProfile(
                profile.getUsername(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getEmail()
        );

        return userService.getUser(profile.getUsername());
    }

    @RequestMapping(value = "profile/photo", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> profilePhoto(@RequestBody ProfilePhotoVO profilePhoto) {
        byte[] photo = Base64.decodeBase64(profilePhoto.getPhoto());
        userService.updateProfilePhoto(
                profilePhoto.getUsername(),
                profilePhoto.getFilename(),
                new ByteArrayInputStream(photo));

        return RestUtils.success();
    }

    @RequestMapping(value = "profile/photo/{username}", method = RequestMethod.GET)
    public void profilePhoto(HttpServletResponse response, @PathVariable String username) {
        try {
            InputStream photo = userService.getProfilePhoto(username);
            if (photo != null) {
                IOUtils.copy(photo, response.getOutputStream());
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch(Exception e) {
            log.error("Error sending /profile/photo to response", e);
        }
    }

    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public @ResponseBody User getUserByUsername(Principal principal, @PathVariable String username) {
        return userService.getUser(username);
    }
}
