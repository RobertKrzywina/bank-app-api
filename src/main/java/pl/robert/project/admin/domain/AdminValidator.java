package pl.robert.project.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.admin.domain.dto.AdminDto;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.admin.domain.dto.ReadAdminDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class AdminValidator implements Validator, AdminValidationStrings {

    private AdminRepository adminRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.isAssignableFrom(CreateAdminDto.class) ||
               (clazz.isAssignableFrom(ReadAdminDto.class))  ||
               (clazz.isAssignableFrom(DeleteAdminDto.class)));
    }

    @Override
    public void validate(Object obj, Errors errors) {

        if (obj instanceof CreateAdminDto) {
            CreateAdminDto dto = (CreateAdminDto) obj;

            validateCreateAdmin(dto, errors);

        } else if (obj instanceof ReadAdminDto) {
            ReadAdminDto dto = (ReadAdminDto) obj;

            validateReadAdmin(dto, errors);

        } else if (obj instanceof DeleteAdminDto) {
            DeleteAdminDto dto = (DeleteAdminDto) obj;

            validateDeleteAdmin(dto, errors);
        }

        ((AdminDto) obj).setErrors(errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));
    }

    private void validateCreateAdmin(CreateAdminDto dto, Errors errors) {

        if (dto.getName() != null) {

            if (dto.getName().length() < MIN_LENGTH_NAME || dto.getName().length() > MAX_LENGTH_NAME) {
                errors.reject(NAME_LENGTH, WRONG_NAME_LENGTH);
            }

        } else {
            errors.reject(NAME_NULL, NAME_REQUIRED);
        }

        if (dto.getLogin() != null) {

            if (dto.getLogin().length() < MIN_LENGTH_LOGIN || dto.getLogin().length() > MAX_LENGTH_LOGIN) {
                errors.reject(LOGIN_LENGTH, WRONG_LOGIN_LENGTH);
            }

            if (isLoginExists(dto.getLogin()) && dto.getLogin().length() >= MIN_LENGTH_LOGIN) {
                errors.reject(LOGIN_EXISTS, WRONG_ADMIN_LOGIN);
            }

        } else {
            errors.reject(LOGIN_NULL, LOGIN_REQUIRED);
        }

        if (dto.getPassword() != null) {

            if (dto.getPassword().length() < MIN_LENGTH_PASSWORD || dto.getPassword().length() > MAX_LENGTH_PASSWORD) {
                errors.reject(PASSWORD_LENGTH, WRONG_PASSWORD_LENGTH);
            }

            if (!dto.getRePassword().equals(dto.getPassword())) {
                errors.reject(RE_PASSWORD_NOT_MATCH, RE_PASSWORD_NOT_MATCH_PASSWORD);
            }

        } else {
            errors.reject(PASSWORD_NULL, PASSWORD_REQUIRED);
        }

        if (dto.getSpecialPassword() != null) {

            if (dto.getSpecialPassword().length() < MIN_LENGTH_SPECIAL_PASSWORD || dto.getSpecialPassword().length() > MAX_LENGTH_SPECIAL_PASSWORD) {
                errors.reject(SPECIAL_PASSWORD_LENGTH, WRONG_SPECIAL_PASSWORD_LENGTH);
            }

            if (!dto.getReSpecialPassword().equals(dto.getRePassword())) {
                errors.reject(RE_SPECIAL_PASSWORD_NOT_MATCH, RE_SPECIAL_PASSWORD_NOT_MATCH_PASSWORD);
            }

        } else {
            errors.reject(SPECIAL_PASSWORD_NULL, SPECIAL_PASSWORD_REQUIRED);
        }
    }

    private void validateReadAdmin(ReadAdminDto dto, Errors errors) {
        if (adminRepo.findById(dto.getId()) == null) {
            errors.reject(ADMIN_NOT_EXISTS, NO_ADMIN);
        }
    }

    private void validateDeleteAdmins(List<Admin> admins, Errors errors) {
        if (admins == null) {
            errors.reject(ADMINS_NOT_EXISTS, NO_ADMINS);
        }


    }

    private void validateDeleteAdmin(DeleteAdminDto dto, Errors errors) {
        Admin admin = adminRepo.findById(dto.getId());

        if (admin == null) {
            errors.reject(ADMIN_NOT_EXISTS, ADMIN_ID_NOT_EXISTS);
        } else {

            if (admin.isHeadAdmin()) {
                errors.reject(HEAD_ADMIN, CANT_DELETE_HEAD_ADMIN);
            }
        }
    }

    private boolean isLoginExists(String login) {
        return adminRepo.findByLogin(login) != null;
    }
}
