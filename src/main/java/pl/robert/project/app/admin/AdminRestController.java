package pl.robert.project.app.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.app.admin.domain.AdminFacade;
import pl.robert.project.app.admin.domain.dto.*;
import pl.robert.project.app.admin.query.ChangeAdminPasswordQueryDto;
import pl.robert.project.app.admin.query.CreateAdminQueryDto;
import pl.robert.project.app.admin.query.DeleteAdminQueryDto;
import pl.robert.project.app.admin.query.ReadAdminQueryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin-panel")
@CrossOrigin("http://localhost:4200")
class AdminRestController {

    private AdminFacade facade;

    public AdminRestController(AdminFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/admin")
    public ResponseEntity create(@RequestBody @Valid CreateAdminDto dto, BindingResult result) {
        CreateAdminQueryDto newAdminDto = facade.add(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(201).body(newAdminDto);
    }

    @GetMapping("/admins")
    public ResponseEntity read() {
        List<ReadAdminQueryDto> adminsDto = facade.getAll();

        return ResponseEntity.status(200).body(adminsDto);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity readById(@PathVariable("id") String id, ReadAdminDto dto, BindingResult result) {
        ReadAdminQueryDto adminDto = facade.getAdminById(dto, Long.parseLong(id), result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(adminDto);
    }

    @DeleteMapping("/admin")
    public ResponseEntity delete() {
        DeleteAdminQueryDto dtoMsg = facade.delete();

        return ResponseEntity.status(200).body(dtoMsg);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id, DeleteAdminDto dto, BindingResult result) {
        DeleteAdminQueryDto dtoMsg = facade.deleteById(dto, Long.parseLong(id), result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dtoMsg);
    }

    @PutMapping("/admin/change-password")
    public ResponseEntity changeAdminPassword(@RequestBody @Valid ChangeAdminPasswordDto dto, BindingResult result) {
        ChangeAdminPasswordQueryDto dtoNewPassword = facade.changePassword(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity.status(400).body(dto.getErrors());
        }

        return ResponseEntity.status(200).body(dtoNewPassword);
    }
}
