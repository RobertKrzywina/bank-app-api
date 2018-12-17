package pl.robert.project.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.admin.domain.AdminFacade;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.admin.domain.dto.ReadAdminDto;
import pl.robert.project.admin.query.CreateAdminQueryDto;
import pl.robert.project.admin.query.DeleteAdminQueryDto;
import pl.robert.project.admin.query.ReadAdminQueryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://localhost:4200")
class AdminRestController {

    private AdminFacade facade;

    public AdminRestController(AdminFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateAdminDto dto,
                                 BindingResult result) {
        CreateAdminQueryDto newAdminDto = facade.add(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(dto.getErrors());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newAdminDto);
    }

    @GetMapping
    public ResponseEntity read() {
        List<ReadAdminQueryDto> adminsDto = facade.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminsDto);
    }

    @GetMapping("{id}")
    public ResponseEntity readById(@PathVariable("id") String id, ReadAdminDto dto,
                                   BindingResult result) {
        ReadAdminQueryDto adminDto = facade.getAdminById(dto, Long.parseLong(id), result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(dto.getErrors());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminDto);
    }

    @DeleteMapping
    public ResponseEntity delete() {
        DeleteAdminQueryDto dtoMsg = facade.delete();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dtoMsg);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id, DeleteAdminDto dto,
                                     BindingResult result) {
        DeleteAdminQueryDto dtoMsg = facade.deleteById(dto, Long.parseLong(id), result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(dto.getErrors());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dtoMsg);
    }
}
