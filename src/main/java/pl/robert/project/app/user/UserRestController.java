package pl.robert.project.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.project.app.user.domain.UserFacade;

@RestController
@RequestMapping("/api/user-panel")
@CrossOrigin("http://localhost:4200")
class UserRestController {

    private UserFacade facade;

    @Autowired
    public UserRestController(UserFacade facade) {
        this.facade = facade;
    }

    // user-panel after sign in
}
