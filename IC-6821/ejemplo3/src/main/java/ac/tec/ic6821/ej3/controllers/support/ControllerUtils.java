package ac.tec.ic6821.ej3.controllers.support;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

public class ControllerUtils {
    public static String getCurrentUsername(Principal principal) {
        return ((UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUsername();
    }
}
