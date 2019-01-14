package pl.robert.project.app.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;
import pl.robert.project.app.admin.query.*;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.security.dto.AppUserDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class AdminFacade implements AdminValidationStrings {

    private AdminRepository repository;
    private AdminFactory factory;
    private AdminValidator validator;
    private BaseAdminQuery baseQuery;
    private CreateAdminDto createAdminDto;
    private ReadAdminDto readAdminDto;
    private DeleteAdminDto deleteAdminDto;
    private ChangeAdminPasswordDto changePasswordDto;

    public CreateAdminQueryDto add(CreateAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                createAdminDto.setName(dto.getName());
                createAdminDto.setLogin(dto.getLogin());
                createAdminDto.setPassword(dto.getPassword());
                createAdminDto.setDecodedBCryptPassword(dto.getPassword());

                dto.setPassword(passwordEncoder.encode(createAdminDto.getPassword()));
                dto.setDecodedBCryptPassword(createAdminDto.getDecodedBCryptPassword());

                Role role = verifyRole(dto.getRoleName());
                dto.getRoles().add(role);

                createAdminDto.setRoleName(dto.getRoleName());
                createAdminDto.setRoles(dto.getRoles());

                repository.saveAndFlush(factory.create(dto));

                return baseQuery.query(createAdminDto);
            }
        }

        return null;
    }

    private Role verifyRole(String roleToVerify) {
        Role role = new Role();

        final String roleHeadAdmin = "ROLE_HEAD-ADMIN";
        final String roleAdmin = "ROLE_ADMIN";

        if (roleToVerify.equals(roleHeadAdmin)) {
            role.setId(1L);
            role.setRole(roleHeadAdmin);
        } else {
            role.setId(2L);
            role.setRole(roleAdmin);
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
                deleteAdminDto.setMessage(M_ADMIN_DELETED);

                return baseQuery.query(deleteAdminDto);
            }
        }

        return null;
    }

    public ChangeAdminPasswordQueryDto changePassword(ChangeAdminPasswordDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {
                repository.updateAdminPassword(dto.getNewPassword(), dto.getId());
                changePasswordDto.setNewPassword(dto.getNewPassword());
            }

            return baseQuery.query(changePasswordDto);
        }

        return null;
    }

    public HashMap<String, Object> aboutMe(Authentication authentication) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("NAME", authentication.getName());
        map.put("AUTHORITIES", authentication.getAuthorities());
        map.put("CREDENTIALS", authentication.getCredentials());
        map.put("DETIALS", authentication.getDetails());
        map.put("PRINCIPAL", authentication.getPrincipal());
        map.put("CLASS", authentication.getClass());

        return map;
    }

    public AppUserDto getAppUser(String login) {
        Admin admin = repository.findByLogin(login);

        if (admin != null) {
            return new AppUserDto(
                    admin.getLogin(),
                    admin.getPassword(),
                    admin.getRoles()
            );
        }

        return null;
    }
}
