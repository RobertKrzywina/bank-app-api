package pl.robert.project.app.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.admin.domain.dto.*;
import pl.robert.project.app.admin.query.*;
import pl.robert.project.app.role.Role;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AdminFacade implements AdminValidationStrings {

    private AdminRepository repository;
    private AdminFactory factory;
    private AdminValidator validator;
    private BaseQuery baseQuery;
    private CreateAdminDto createAdminDto;
    private ReadAdminDto readAdminDto;
    private DeleteAdminDto deleteAdminDto;
    private ChangeAdminPasswordDto changePasswordDto;
    private ChangeAdminSpecialPasswordDto changeSpecialPasswordDto;

    public CreateAdminQueryDto add(CreateAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                createAdminDto.setName(dto.getName());
                createAdminDto.setLogin(dto.getLogin());
                createAdminDto.setPassword(dto.getPassword());
                createAdminDto.setSpecialPassword(dto.getReSpecialPassword());
                createAdminDto.setRePassword(dto.getRePassword());
                createAdminDto.setReSpecialPassword(dto.getReSpecialPassword());

                Role role = verifyRole(dto.getRoleName());
                dto.getRoles().add(role);

                createAdminDto.setRoleName(dto.getRoleName());
                createAdminDto.setRoles(dto.getRoles());

                repository.saveAndFlush(factory.create(dto));

                //updateAdminsId();

                return baseQuery.query(createAdminDto);
            }
        }

        return null;
    }

    private Role verifyRole(String roleToVerify) {
        Role role = new Role();

        final String roleHeadAdmin = "ROLE_HEAD-ADMIN";
        final String roleAdmin = "ROLE_ADMIN";
        final String roleUser = "ROLE_USER";

        switch (roleToVerify) {
            case roleHeadAdmin:
                role.setRoleId(1L);
                role.setRoleName(roleHeadAdmin);
                break;

            case roleAdmin:
                role.setRoleId(2L);
                role.setRoleName(roleAdmin);
                break;

            case roleUser:
                role.setRoleId(3L);
                role.setRoleName(roleUser);
                break;
        }

        return role;
    }

    public List<ReadAdminQueryDto> getAll() {
        List<Admin> admins = repository.findAll();
        List<ReadAdminQueryDto> adminsDto = new ArrayList<>();

        for (Admin admin : admins) {
            adminsDto.add(new ReadAdminQueryDto(
                    admin.getId(),
                    admin.getName(),
                    admin.getRoles()
            ));
        }

        return adminsDto;
    }

    public ReadAdminQueryDto getAdminById(ReadAdminDto dto, long id, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                Admin admin = repository.findById(id);

                readAdminDto.setId(admin.getId());
                readAdminDto.setName(admin.getName());
                readAdminDto.setRoles(admin.getRoles());

                return baseQuery.query(readAdminDto);
            }
        }

        return null;
    }

    public DeleteAdminQueryDto delete() {
        List<Admin> admins = repository.findAll();

        if (admins != null) {
            repository.deleteAdminsExceptHeadAdmin();
            //updateAdminsId();
            deleteAdminDto.setMessage(M_DELETED_ALL_ADMINS);
        } else {
            deleteAdminDto.setMessage(M_NO_ADMINS);
        }

        return baseQuery.query(deleteAdminDto);
    }

    public DeleteAdminQueryDto deleteById(DeleteAdminDto dto, long id, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {
                repository.deleteById(id);
                //updateAdminsId();
                deleteAdminDto.setMessage(M_ADMIN_DELETED);

                return baseQuery.query(deleteAdminDto);
            }
        }

        return null;
    }

    public ChangeAdminPasswordQueryDto changePassword(Object obj, BindingResult result) {
        if (validator.supports(obj.getClass())) {
            if (obj instanceof ChangeAdminPasswordDto) {
                ChangeAdminPasswordDto dto = (ChangeAdminPasswordDto) obj;

                validator.validate(dto, result);

                if (!result.hasErrors()) {
                    repository.updateAdminPassword(dto.getNewPassword(), dto.getId());
                    changePasswordDto.setNewPassword(dto.getNewPassword());
                }

            } else {
                ChangeAdminSpecialPasswordDto dto = (ChangeAdminSpecialPasswordDto) obj;

                validator.validate(dto, result);

                if (!result.hasErrors()) {
                    repository.updateAdminSpecialPassword(dto.getNewSpecialPassword(), dto.getId());
                    changeSpecialPasswordDto.setNewSpecialPassword(dto.getNewSpecialPassword());
                }
            }

            return baseQuery.query(obj);
        }

        return null;
    }

    private void updateAdminsId() {
        List<Admin> admins = repository.findAll();
        long checker = 0L;
        for (Admin admin : admins) {
            if (admin.getId() != ++checker) {
                repository.updateAdminId(checker, admin.getId());
            }
        }
    }
}
