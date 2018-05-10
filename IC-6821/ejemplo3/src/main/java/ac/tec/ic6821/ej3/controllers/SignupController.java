package ac.tec.ic6821.ej3.controllers;

import ac.tec.ic6821.ej3.model.User;
import ac.tec.ic6821.ej3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doSignup(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirm) {

        if (!password.equals(confirm)) {
            return "redirect:/signup?passwordMismatch";
        }

        User newUser = userService.signUp(username, password, confirm);

        if (newUser == null) {
            return "redirect:/signup?usernameAlreadyExists";
        } else {
            return "forward:/login";
        }
    }
}
