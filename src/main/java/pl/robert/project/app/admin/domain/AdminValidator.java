package pl.robert.project.app.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.app.admin.domain.dto.AdminDto;
import pl.robert.project.app.admin.domain.dto.ChangeAdminPasswordDto;
import pl.robert.project.app.admin.domain.dto.CreateAdminDto;
import pl.robert.project.app.admin.domain.dto.ReadAdminDto;

@Component
@AllArgsConstructor
class AdminValidator implements Validator, AdminValidationStrings {

    private AdminRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CreateAdminDto.class) ||
               clazz.isAssignableFrom(ReadAdminDto.class) ||
               clazz.isAssignableFrom(ChangeAdminPasswordDto.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        if (obj instanceof CreateAdminDto) {
            CreateAdminDto dto = (CreateAdminDto) obj;

            validateCreateAdmin(dto, errors);

        } else if (obj instanceof ReadAdminDto) {
            ReadAdminDto dto = (ReadAdminDto) obj;

            validateReadAdmin(dto, errors);
        }

        ((AdminDto) obj).setErrors(errors.getAllErrors());
    }

    void validateGetAllAdmins(ReadAdminDto dto, Errors errors) {

        if (repository.findAll().isEmpty()) {
            errors.reject(C_ADMINS_NOT_EXISTS, M_ADMINS_NOT_EXISTS);
        }

        dto.setErrors(errors.getAllErrors());
    }

    void validateGetAllAdminsExceptHeadAdmins(ReadAdminDto dto, Errors errors) {

        if (repository.findAllAdminsExceptHeadAdmins().isEmpty()) {
            errors.reject(C_ADMINS_NOT_EXISTS, M_ADMINS_NOT_EXISTS);
        }

        dto.setErrors(errors.getAllErrors());
    }

    void validateGetAdminExceptHeadAdmin(long id, ReadAdminDto dto, Errors errors) {

        if (repository.findByIdExceptHeadAdmin(id) == null) {
            errors.reject(C_ADMIN_NOT_EXISTS, M_ADMIN_ID_NOT_EXISTS);
        }

        dto.setErrors(errors.getAllErrors());
    }

    private void validateCreateAdmin(CreateAdminDto dto, Errors errors) {
        if (dto.getName() != null) {
            
            if (isFieldLengthCorrect(dto.getName(), MIN_LENGTH_NAME, MAX_LENGTH_NAME)) {
                errors.reject(C_NAME_LENGTH, M_NAME_LENGTH);
            }
            
        } else {
            errors.reject(C_NAME_NULL, M_NAME_NULL);
        }

        if (dto.getLogin() != null) {
            
            if (isFieldLengthCorrect(dto.getLogin(), MIN_LENGTH_LOGIN, MAX_LENGTH_LOGIN)) {
                errors.reject(C_LOGIN_LENGTH, M_LOGIN_LENGTH);
            }

            if (isLoginExists(dto.getLogin()) && dto.getLogin().length() >= MIN_LENGTH_LOGIN) {
                errors.reject(C_ADMIN_LOGIN_EXISTS, M_ADMIN_LOGIN_EXISTS);
            }
            
        } else {
            errors.reject(C_LOGIN_NULL, M_LOGIN_NULL);
        }

        if (dto.getPassword() != null) {
            
            if (isFieldLengthCorrect(dto.getPassword(), MIN_LENGTH_PASSWORD, MAX_LENGTH_PASSWORD)) {
                errors.reject(C_PASSWORD_LENGTH, M_PASSWORD_LENGTH);
            }

            if (!dto.getRePassword().equals(dto.getPassword())) {
                errors.reject(C_RE_PASSWORD_NOT_MATCH, M_RE_PASSWORD_NOT_MATCH_PASSWORD);
            } 
            
        } else {
            errors.reject(C_PASSWORD_NULL, M_PASSWORD_NULL);
        }

        if (dto.getRoleName() != null) {
            
            if (!isRoleCorrect(dto.getRoleName())) {
                errors.reject(C_INCORRECT_ROLE_NAME, M_INCORRECT_ROLE_NAME);
            }
            
        } else {
            errors.reject(C_ROLE_NAME_NULL, M_ROLE_NAME_NULL);
        }
    }

    private boolean isRoleCorrect(String roleName) {
        return roleName.equals(ROLE_HEAD_ADMIN) ||
               roleName.equals(ROLE_ADMIN);
    }

    private void validateReadAdmin(ReadAdminDto dto, Errors errors) {

        if (repository.findById(dto.getId()) == null) {
            errors.reject(C_ADMIN_NOT_EXISTS, M_ADMIN_ID_NOT_EXISTS);
        }
    }

    void validateChangeAdminPassword(ChangeAdminPasswordDto dto, Errors errors) {
        Admin admin = repository.findById(dto.getId());

        if (admin == null) {
            errors.reject(C_ADMIN_NOT_EXISTS, M_ADMIN_ID_NOT_EXISTS);

        } else {

            if (dto.getOldPassword() != null) {

                if (!dto.getOldPassword().equals(admin.getDecodedBCryptPassword())) {
                    errors.reject(C_OLD_PASSWORD_NOT_MATCH, M_OLD_PASSWORD_NOT_MATCH);
                }

            } else {
                errors.reject(C_OLD_PASSWORD_NULL, M_OLD_PASSWORD_NULL);
            }

            if (dto.getReOldPassword() != null) {
                
                if (!dto.getOldPassword().equals(dto.getReOldPassword())) {
                    errors.reject(C_RE_OLD_PASSWORD_NOT_MATCH, M_RE_OLD_PASSWORD_NOT_MATCH);
                }
                
            } else {
                errors.reject(C_RE_OLD_PASSWORD_NULL, M_RE_OLD_PASSWORD_NULL);
            }

            if (dto.getNewPassword() != null) {
                
                if (isFieldLengthCorrect(dto.getNewPassword(), MIN_LENGTH_PASSWORD, MAX_LENGTH_PASSWORD)) {
                    errors.reject(C_PASSWORD_LENGTH, M_PASSWORD_LENGTH);
                }
                
            } else {
                errors.reject(C_NEW_PASSWORD_NULL, M_NEW_PASSWORD_NULL);
            }

            if (dto.getReNewPassword() != null) {
                
                if (!dto.getNewPassword().equals(dto.getReNewPassword())) {
                    errors.reject(C_RE_NEW_PASSWORD_NOT_MATCH, M_RE_NEW_PASSWORD_NOT_MATCH);
                } 
                
            } else {
                errors.reject(C_RE_NEW_PASSWORD_NULL, M_RE_NEW_PASSWORD_NULL);
            }
        }

        dto.setErrors(errors.getAllErrors());
    }

    private boolean isLoginExists(String login) {
        return repository.findByLogin(login) != null;
    }

    private boolean isFieldLengthCorrect(String fieldToCheck, int minLength, int maxLength) {
        return fieldToCheck.length() < minLength || fieldToCheck.length() > maxLength;
    }
}
