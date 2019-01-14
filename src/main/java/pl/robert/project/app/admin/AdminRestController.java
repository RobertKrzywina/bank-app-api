package pl.robert.project.app.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.app.admin.domain.AdminFacade;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;
import pl.robert.project.app.admin.query.ChangeAdminPasswordQueryDto;
import pl.robert.project.app.admin.query.CreateAdminQueryDto;
import pl.robert.project.app.admin.query.DeleteAdminQueryDto;
import pl.robert.project.app.admin.query.ReadAdminQueryDto;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.DeleteUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.query.DeleteUserQueryDto;
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

    @PostMapping("/admin")
    public ResponseEntity create(@RequestBody @Valid CreateAdminDto dto, BindingResult result) {
        CreateAdminQueryDto newAdminDto = adminFacade.add(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(201).body(newAdminDto);
    }

    @GetMapping
    public HashMap<String, Object> aboutMe(Authentication auth) {
        if (auth != null) {
            return adminFacade.aboutMe(auth);
        }

        return null;
    }

    @GetMapping("/admins")
    public ResponseEntity read() {
        List<ReadAdminQueryDto> adminsDto = adminFacade.getAll();

        return ResponseEntity.status(200).body(adminsDto);
    }

    @GetMapping("/users")
    public ResponseEntity readUsers() {
        List<ReadUserQueryDto> usersDto = userFacade.getAll();

        return ResponseEntity.status(200).body(usersDto);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity readById(@PathVariable("id") String id, ReadAdminDto dto, BindingResult result) {
        ReadAdminQueryDto adminDto = adminFacade.getAdminById(dto, Long.parseLong(id), result);

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
        DeleteUserQueryDto dtoMsg = userFacade.delete();

        return ResponseEntity.status(200).body(dtoMsg);
    }

    @DeleteMapping("/users/{pesel}")
    public ResponseEntity deleteUserByPesel(@PathVariable("pesel") String pesel, DeleteUserDto dto, BindingResult result) {
        DeleteUserQueryDto dtoMsg = userFacade.deleteUserByPesel(pesel, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dtoMsg);
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @DeleteMapping("/admins")
    public ResponseEntity deleteAdmins() {
        DeleteAdminQueryDto dtoMsg = adminFacade.delete();

        return ResponseEntity.status(200).body(dtoMsg);
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @DeleteMapping("/admins/{id}")
    public ResponseEntity deleteAdminById(@PathVariable("id") String id, DeleteAdminDto dto, BindingResult result) {
        DeleteAdminQueryDto dtoMsg = adminFacade.deleteById(dto, Long.parseLong(id), result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dtoMsg);
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
