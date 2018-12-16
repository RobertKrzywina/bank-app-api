package pl.robert.project.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.admin.domain.AdminFacade;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.query.CreateAdminQueryDto;

import javax.validation.Valid;

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
        CreateAdminQueryDto newAdmin = facade.add(dto, result);

        if (!dto.getErrors().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(dto.getErrors());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newAdmin);
    }
}
