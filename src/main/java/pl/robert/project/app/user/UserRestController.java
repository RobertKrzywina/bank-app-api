package pl.robert.project.app.user;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.DeleteUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.query.CreateUserQueryDto;
import pl.robert.project.app.user.query.DeleteUserQueryDto;
import pl.robert.project.app.user.query.ReadUserQueryDto;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/admin-panel/users")
    public ResponseEntity readUsers() {
        List<ReadUserQueryDto> usersDto = facade.getAll();

        return ResponseEntity.status(200).body(usersDto);
    }

    @GetMapping("/admin-panel/users/{pesel}")
    public ResponseEntity readUserByPesel(@PathVariable("pesel") String pesel, ReadUserDto dto, BindingResult result) {
        ReadUserQueryDto userDto = facade.getUserByPesel(pesel, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(userDto);
    }

    @DeleteMapping("/admin-panel/users")
    public ResponseEntity delete() {
        DeleteUserQueryDto dtoMsg = facade.delete();

        return ResponseEntity.status(200).body(dtoMsg);
    }

    @DeleteMapping("/admin-panel/users/{pesel}")
    public ResponseEntity deleteById(@PathVariable("pesel") String pesel, DeleteUserDto dto, BindingResult result) {
        DeleteUserQueryDto dtoMsg = facade.deleteUserByPesel(pesel, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dtoMsg);
    }
}
