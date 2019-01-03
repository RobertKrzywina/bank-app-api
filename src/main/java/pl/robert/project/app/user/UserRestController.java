package pl.robert.project.app.user;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.project.app.user.domain.UserFacade;

import java.util.HashMap;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/user-panel")
@CrossOrigin("http://localhost:4200")
class UserRestController {

    private UserFacade facade;

    public UserRestController(UserFacade facade) {
        this.facade = facade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> aboutMe(Authentication auth) {
        if (auth != null) {
            return facade.aboutMe(auth);
        }

        return null;
    }
}
