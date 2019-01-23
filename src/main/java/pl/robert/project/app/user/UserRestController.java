package pl.robert.project.app.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.query.AboutMeUserQueryDto;

import javax.validation.Valid;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/user-panel")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
class UserRestController {

    private UserFacade userFacade;

    @GetMapping("/about-me")
    public ResponseEntity aboutMe(Authentication auth) {
        AboutMeUserQueryDto aboutMeQuery = userFacade.aboutMe(auth);

        if (aboutMeQuery == null) {
            return ResponseEntity.status(404).body(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(200).body(aboutMeQuery);
    }

    @PostMapping("/send-transaction")
    public ResponseEntity sendTransaction(Authentication auth,
                                          @RequestBody @Valid SendTransactionDto dto, BindingResult result) {
        userFacade.sendTransaction(auth, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(HttpStatus.OK);
    }
}
