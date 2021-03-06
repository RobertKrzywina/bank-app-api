package pl.robert.project.app.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.admin.domain.dto.AboutMeAdminDto;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;
import pl.robert.project.app.admin.query.AboutMeAdminQueryDto;
import pl.robert.project.app.admin.query.BaseAdminQuery;
import pl.robert.project.app.admin.query.CreateAdminQueryDto;
import pl.robert.project.app.admin.query.ReadAdminQueryDto;
import pl.robert.project.app.role.Role;
import pl.robert.project.app.security.dto.AppUserDto;

import java.util.*;

@AllArgsConstructor
public class AdminFacade implements AdminValidationStrings {

    private AdminRepository repository;
    private AdminFactory factory;
    private AdminValidator validator;
    private BaseAdminQuery baseQuery;
    private CreateAdminDto createAdminDto;
    private ReadAdminDto readAdminDto;
    private ChangeAdminPasswordDto changePasswordDto;
    private AboutMeAdminDto aboutMeDto;

    public CreateAdminQueryDto add(CreateAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                createAdminDto.setName(dto.getName());
                createAdminDto.setLogin(dto.getLogin());
                createAdminDto.setPassword(dto.getPassword());
                createAdminDto.setRePassword(dto.getRePassword());
                dto.setPassword(passwordEncoder.encode(createAdminDto.getPassword()));

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

        if (roleToVerify.equals(ROLE_HEAD_ADMIN)) {
            role.setId(1L);
            role.setRole(ROLE_HEAD_ADMIN);
        } else {
            role.setId(2L);
            role.setRole(ROLE_ADMIN);
        }

        return role;
    }

    public List<ReadAdminQueryDto> getAllAdmins(ReadAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            List<Admin> admins = repository.findAll();
            validator.validateGetAllAdmins(dto, result);

            if (!result.hasErrors()) {
                List<ReadAdminQueryDto> adminsDto = new ArrayList<>();

                for (Admin admin : admins) {
                    adminsDto.add(new ReadAdminQueryDto(
                            admin.getId(),
                            admin.getName(),
                            admin.getRoleName()
                    ));
                }

                return adminsDto;
            }
        }

        return null;
    }

    public ReadAdminQueryDto getAdminById(long id, ReadAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                Admin admin = repository.findById(id);

                readAdminDto.setId(admin.getId());
                readAdminDto.setName(admin.getName());
                readAdminDto.setRoleName(admin.getRoleName());

                return baseQuery.query(readAdminDto);
            }
        }

        return null;
    }

    public void deleteAllAdminsExceptHeadAdmins(ReadAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validateGetAllAdminsExceptHeadAdmins(dto, result);

            if (!result.hasErrors()) {
                repository.deleteAdminsExceptHeadAdmins();
            }
        }
    }

    public void deleteAdminById(long id, ReadAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validateGetAdminExceptHeadAdmin(id, dto, result);

            if (!result.hasErrors()) {
                repository.deleteById(id);
            }
        }
    }

    public void changeAdminPassword(long id, ChangeAdminPasswordDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            dto.setId(id);

            validator.validateChangeAdminPassword(dto, result);

            if (!result.hasErrors()) {

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                repository.updateAdminPassword(passwordEncoder.encode(dto.getNewPassword()), id);
                repository.updateAdminDecodedBCryptPassword(dto.getNewPassword(), id);
                changePasswordDto.setNewPassword(dto.getNewPassword());
            }
        }
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

    public AboutMeAdminQueryDto aboutMe(Authentication auth) {
        Admin admin = repository.findByLogin(auth.getName());

        if (admin != null) {

            aboutMeDto.setName(admin.getName());
            aboutMeDto.setLogin(admin.getLogin());
            aboutMeDto.setPassword(admin.getDecodedBCryptPassword());
            aboutMeDto.setRoleName(admin.getRoleName());

            return baseQuery.query(aboutMeDto);
        }

        return null;
    }
}
