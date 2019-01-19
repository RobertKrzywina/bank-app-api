package pl.robert.project.app.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.app.admin.domain.AdminFacade;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;
import pl.robert.project.app.admin.query.ChangeAdminPasswordQueryDto;
import pl.robert.project.app.admin.query.CreateAdminQueryDto;
import pl.robert.project.app.admin.query.ReadAdminQueryDto;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.query.ReadUserQueryDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_HEAD-ADMIN') or hasRole('ROLE_ADMIN')")
@RequestMapping("/api/admin-panel")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
class AdminRestController {

    private AdminFacade adminFacade;
    private UserFacade userFacade;

    @GetMapping
    public HashMap<String, Object> aboutMe(Authentication auth) {
        if (auth != null) {
            return adminFacade.aboutMe(auth);
        }

        return null;
    }

    @PostMapping("/admin")
    public ResponseEntity create(@RequestBody @Valid CreateAdminDto dto, BindingResult result) {
        CreateAdminQueryDto newAdminDto = adminFacade.add(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(201).body(newAdminDto);
    }

    @GetMapping("/admins")
    public ResponseEntity<?> read() {
        List<ReadAdminQueryDto> adminsDto = adminFacade.getAll();

        if (adminsDto != null) {
            return ResponseEntity.status(200).body(adminsDto);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users")
    public ResponseEntity<?> readUsers() {
        List<ReadUserQueryDto> usersDto = userFacade.getAll();

        if (usersDto != null) {
            return ResponseEntity.status(200).body(usersDto);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity readById(@PathVariable("id") String id, ReadAdminDto dto, BindingResult result) {
        ReadAdminQueryDto adminDto = adminFacade.getAdminById(Long.parseLong(id), dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(adminDto);
    }

    @GetMapping("/users/{pesel}")
    public ResponseEntity readUserByPesel(@PathVariable("pesel") String pesel, ReadUserDto dto, BindingResult result) {
        ReadUserQueryDto userDto = userFacade.getUserByPesel(pesel, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(userDto);
    }

    @DeleteMapping("/users")
    public ResponseEntity deleteUsers() {
        List<ReadUserQueryDto> usersDto = userFacade.getAll();
        String msg = userFacade.delete();

        if (usersDto != null) {
            return ResponseEntity.status(200).body(msg);
        }

        return ResponseEntity.status(404).body(msg);
    }

    @DeleteMapping("/users/{pesel}")
    public ResponseEntity deleteUserByPesel(@PathVariable("pesel") String pesel, ReadUserDto dto, BindingResult result) {
        ReadUserQueryDto userDto = userFacade.getUserByPesel(pesel, dto, result);

        if (userDto != null) {
            String msg = userFacade.deleteUserByPesel(pesel);
            return ResponseEntity.status(200).body(msg);
        }

        return ResponseEntity.status(400).body(dto.getErrors());
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @DeleteMapping("/admins")
    public ResponseEntity deleteAdmins() {
        List<ReadAdminQueryDto> adminsDto = adminFacade.getAllAdminsExceptHeadAdmins();
        String msg = adminFacade.deleteAllAdminsExceptHeadAdmin();

        if (adminsDto != null) {
            return ResponseEntity.status(200).body(msg);
        }

        return ResponseEntity.status(404).body(msg);
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @DeleteMapping("/admins/{id}")
    public ResponseEntity deleteAdminById(@PathVariable("id") String id, ReadAdminDto dto, BindingResult result) {
        ReadAdminQueryDto adminDto = adminFacade.getAdminById(Long.parseLong(id), dto, result);

        if (adminDto != null) {
            String msg = adminFacade.deleteById(Long.parseLong(id));
            return ResponseEntity.status(200).body(msg);
        }

        return ResponseEntity.status(400).body(dto.getErrors());
    }

    @PutMapping("/admin/change-password")
    public ResponseEntity changeAdminPassword(@RequestBody @Valid ChangeAdminPasswordDto dto, BindingResult result) {
        ChangeAdminPasswordQueryDto dtoNewPassword = adminFacade.changePassword(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dtoNewPassword);
    }
}
