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
import pl.robert.project.app.admin.query.AboutMeAdminQueryDto;
import pl.robert.project.app.admin.query.CreateAdminQueryDto;
import pl.robert.project.app.admin.query.ReadAdminQueryDto;
import pl.robert.project.app.transaction.domain.TransactionFacade;
import pl.robert.project.app.transaction.domain.dto.ReadTransactionDto;
import pl.robert.project.app.transaction.query.ReadTransactionQueryDto;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.ChangeUserPasswordDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.query.ReadUserQueryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_HEAD-ADMIN') or hasRole('ROLE_ADMIN')")
@RequestMapping("/api/admin-panel")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
class AdminRestController {

    private AdminFacade adminFacade;
    private UserFacade userFacade;
    private TransactionFacade transactionFacade;

    @GetMapping("/about-me")
    public ResponseEntity aboutMe(Authentication auth) {
        AboutMeAdminQueryDto aboutMeQuery = adminFacade.aboutMe(auth);

        if (aboutMeQuery == null) {
            return ResponseEntity.status(404).body(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(200).body(aboutMeQuery);
    }

    @GetMapping("/admins")
    public ResponseEntity getAdmins(@Valid ReadAdminDto dto, BindingResult result) {
        List<ReadAdminQueryDto> adminsQuery = adminFacade.getAllAdmins(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(adminsQuery);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity getAdminById(@PathVariable("id") String id, ReadAdminDto dto, BindingResult result) {
        ReadAdminQueryDto adminQuery = adminFacade.getAdminById(Long.parseLong(id), dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(adminQuery);
    }

    @GetMapping("/users")
    public ResponseEntity getUsers(@Valid ReadUserDto dto, BindingResult result) {
        List<ReadUserQueryDto> usersQuery = userFacade.getAll(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(usersQuery);
    }

    @GetMapping("/users/{pesel}")
    public ResponseEntity getUserByPesel(@PathVariable("pesel") String pesel, ReadUserDto dto, BindingResult result) {
        ReadUserQueryDto userQuery = userFacade.getUserByPesel(pesel, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(userQuery);
    }

    @GetMapping("/transactions")
    public ResponseEntity getTransactions(@Valid ReadTransactionDto dto, BindingResult result) {
        List<ReadTransactionQueryDto> transactionsQuery = transactionFacade.getAll(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(transactionsQuery);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity getTransactionById(@PathVariable("id") String id, ReadTransactionDto dto, BindingResult result) {
        ReadTransactionQueryDto transactionQuery = transactionFacade.getTransactionById(Long.parseLong(id), dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(transactionQuery);
    }

    @PostMapping("/admin")
    public ResponseEntity createAdmin(@RequestBody @Valid CreateAdminDto dto, BindingResult result) {
        CreateAdminQueryDto newAdminQuery = adminFacade.add(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(201).body(newAdminQuery);
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @DeleteMapping("/admins")
    public ResponseEntity deleteAdmins(@Valid ReadAdminDto dto, BindingResult result) {
        adminFacade.deleteAllAdminsExceptHeadAdmins(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @DeleteMapping("/admins/{id}")
    public ResponseEntity deleteAdminById(@PathVariable("id") String id, @Valid ReadAdminDto dto, BindingResult result) {
        adminFacade.deleteAdminById(Long.parseLong(id), dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity deleteUsers(@Valid ReadUserDto dto, BindingResult result) {
        userFacade.deleteAllUsers(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(HttpStatus.OK);
    }

    @DeleteMapping("/users/{pesel}")
    public ResponseEntity deleteUserByPesel(@PathVariable("pesel") String pesel, @Valid ReadUserDto dto, BindingResult result) {
        userFacade.deleteUserByPesel(pesel, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @PatchMapping("/admin/change-password/{id}")
    public ResponseEntity changeAdminPassword(@PathVariable("id") String id,
                                              @RequestBody @Valid ChangeAdminPasswordDto dto, BindingResult result) {
        adminFacade.changeAdminPassword(Long.parseLong(id), dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dto.getNewPassword());
    }

    @PreAuthorize("hasRole('HEAD-ADMIN')")
    @PatchMapping("/user/change-password/{pesel}")
    public ResponseEntity changeUserPassword(@PathVariable("pesel") String pesel,
                                             @RequestBody @Valid ChangeUserPasswordDto dto, BindingResult result) {
        userFacade.changePassword(pesel, dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dto.getNewPassword());
    }
}
