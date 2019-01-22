package pl.robert.project.app.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.query.AboutMeUserQueryDto;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/user-panel")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
class UserRestController {

    private UserFacade userFacade;

    @GetMapping("/about-me")
    public ResponseEntity aboutMe(Authentication auth) {
        AboutMeUserQueryDto aboutMeUserDto = userFacade.aboutMe(auth);

        if (aboutMeUserDto == null) {
            return ResponseEntity.status(404).body(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(200).body(aboutMeUserDto);
    }
}
