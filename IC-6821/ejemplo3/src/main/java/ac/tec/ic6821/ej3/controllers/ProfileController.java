package ac.tec.ic6821.ej3.controllers;

import ac.tec.ic6821.ej3.controllers.support.ControllerUtils;
import ac.tec.ic6821.ej3.model.User;
import ac.tec.ic6821.ej3.services.UserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(ModelMap modelMap, Principal principal) {
        User user = userService.getCurrentUser(principal);
        modelMap.put("user", user);
        return "profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doUpdateProfile(Principal principal, @ModelAttribute User userModel) {
        String username = ControllerUtils.getCurrentUsername(principal);
        userService.updateProfile(username,
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail());

        return "redirect:/profile";
    }

    @RequestMapping(value = "photo", method = RequestMethod.POST)
    public String doUpdateProfilePhoto(Principal principal, @RequestParam MultipartFile file) {
        try {
            String username = ControllerUtils.getCurrentUsername(principal);
            userService.updateProfilePhoto(username, file.getOriginalFilename(), file.getInputStream());
        } catch (Exception e) {
            log.error("Error reading uploaded file to /profile/photo", e);
        }

        return "redirect:/profile";
    }

    @RequestMapping(value = "photo", method = RequestMethod.GET)
    public void showProfilePhoto(HttpServletResponse response, @RequestParam String username) {
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
}
