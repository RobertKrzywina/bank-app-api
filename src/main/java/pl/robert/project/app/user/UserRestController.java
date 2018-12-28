package pl.robert.project.app.user;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.query.CreateUserQueryDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
class UserRestController {

    private UserFacade facade;

    public UserRestController(UserFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid CreateUserDto dto, BindingResult result) {
        CreateUserQueryDto newUserDto = facade.add(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(201).body(newUserDto);
    }
}
