package pl.robert.project.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.admin.domain.AdminFacade;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.domain.exception.AdminException;
import pl.robert.project.admin.query.AdminQueryDto;

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
    public ResponseEntity<?> create(@RequestBody @Valid CreateAdminDto dto) throws AdminException {
        AdminQueryDto newAdmin = facade.add(dto);

        return ResponseEntity
                .ok(newAdmin);
    }
}
