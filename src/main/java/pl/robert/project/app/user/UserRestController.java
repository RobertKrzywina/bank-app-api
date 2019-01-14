package pl.robert.project.app.user;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
class UserRestController {

    private UserFacade facade;

    @GetMapping
    public HashMap<String, Object> aboutMe(Authentication auth) {
        if (auth != null) {
            return facade.aboutMe(auth);
        }

        return null;
    }
}
