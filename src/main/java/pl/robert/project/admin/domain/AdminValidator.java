package pl.robert.project.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.admin.domain.dto.*;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class AdminValidator implements Validator, AdminValidationStrings {

    private AdminRepository adminRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.isAssignableFrom(CreateAdminDto.class) ||
                (clazz.isAssignableFrom(ReadAdminDto.class)) ||
                (clazz.isAssignableFrom(DeleteAdminDto.class)) ||
                (clazz.isAssignableFrom(ChangeAdminPasswordDto.class)) ||
                (clazz.isAssignableFrom(ChangeAdminSpecialPasswordDto.class)));
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

        } else if (obj instanceof ChangeAdminPasswordDto) {
            ChangeAdminPasswordDto dto = (ChangeAdminPasswordDto) obj;

            validateChangeAdminPassword(dto, errors);

        } else if (obj instanceof ChangeAdminSpecialPasswordDto) {
            ChangeAdminSpecialPasswordDto dto = (ChangeAdminSpecialPasswordDto) obj;

            validateChangeAdminSpecialPassword(dto, errors);
        }

        ((AdminDto) obj).setErrors(errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));
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
            } else {

                if (hasAnyWhiteSpaces(dto.getPassword())) {
                    dto.setPassword(convertAllWhiteSpacesToHash(dto.getPassword()));
                }

            }
        } else {
            errors.reject(C_PASSWORD_NULL, M_PASSWORD_NULL);
        }

        if (dto.getSpecialPassword() != null) {
            if (isFieldLengthCorrect(dto.getSpecialPassword(), MIN_LENGTH_SPECIAL_PASSWORD, MAX_LENGTH_SPECIAL_PASSWORD)) {
                errors.reject(C_SPECIAL_PASSWORD_LENGTH, M_SPECIAL_PASSWORD_LENGTH);
            }

            if (!dto.getSpecialPassword().equals(dto.getReSpecialPassword())) {
                errors.reject(C_RE_SPECIAL_PASSWORD_NOT_MATCH, M_RE_SPECIAL_PASSWORD_NOT_MATCH_PASSWORD);
            } else {

                if (hasAnyWhiteSpaces(dto.getSpecialPassword())) {
                    dto.setSpecialPassword(convertAllWhiteSpacesToHash(dto.getSpecialPassword()));
                }

            }
        } else {
            errors.reject(C_SPECIAL_PASSWORD_NULL, M_SPECIAL_PASSWORD_NULL);
        }
    }

    private void validateReadAdmin(ReadAdminDto dto, Errors errors) {
        if (adminRepo.findById(dto.getId()) == null) {
            errors.reject(C_ADMIN_NOT_EXISTS, M_ADMIN_ID_NOT_EXISTS);
        }
    }

    private void validateDeleteAdmin(DeleteAdminDto dto, Errors errors) {
        Admin admin = adminRepo.findById(dto.getId());

        if (admin == null) {
            errors.reject(C_ADMIN_NOT_EXISTS, M_ADMIN_ID_NOT_EXISTS);
        } else {
            if (admin.isHeadAdmin()) {
                errors.reject(C_HEAD_ADMIN, M_CANT_DELETE_HEAD_ADMIN);
            }
        }
    }

    private void validateChangeAdminPassword(ChangeAdminPasswordDto dto, Errors errors) {
        if (dto.getOldPassword() != null) {
            if (!dto.getOldPassword().equals(adminRepo.findById(dto.getId()).getPassword())) {
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
            } else {

                if (hasAnyWhiteSpaces(dto.getNewPassword())) {
                    dto.setNewPassword(convertAllWhiteSpacesToHash(dto.getNewPassword()));
                }

            }
        } else {
            errors.reject(C_RE_NEW_PASSWORD_NULL, M_RE_NEW_PASSWORD_NULL);
        }
    }

    private void validateChangeAdminSpecialPassword(ChangeAdminSpecialPasswordDto dto, Errors errors) {
        if (dto.getOldSpecialPassword() != null) {
            if (!dto.getOldSpecialPassword().equals(adminRepo.findById(dto.getId()).getSpecialPassword())) {
                errors.reject(C_OLD_SPECIAL_PASSWORD_NOT_MATCH, M_OLD_SPECIAL_PASSWORD_NOT_MATCH);
            }
        } else {
            errors.reject(C_OLD_SPECIAL_PASSWORD_NULL, M_OLD_SPECIAL_PASSWORD_NULL);
        }

        if (dto.getReOldSpecialPassword() != null) {
            if (!dto.getOldSpecialPassword().equals(dto.getReOldSpecialPassword())) {
                errors.reject(C_RE_OLD_SPECIAL_PASSWORD_NOT_MATCH, M_RE_OLD_SPECIAL_PASSWORD_NOT_MATCH);
            }
        } else {
            errors.reject(C_RE_OLD_SPECIAL_PASSWORD_NULL, M_RE_OLD_SPECIAL_PASSWORD_NULL);
        }

        if (dto.getNewSpecialPassword() != null) {
            if (isFieldLengthCorrect(dto.getNewSpecialPassword(), MIN_LENGTH_PASSWORD, MAX_LENGTH_PASSWORD)) {
                errors.reject(C_SPECIAL_PASSWORD_LENGTH, M_SPECIAL_PASSWORD_LENGTH);
            }
        } else {
            errors.reject(C_NEW_SPECIAL_PASSWORD_NULL, M_NEW_SPECIAL_PASSWORD_NULL);
        }

        if (dto.getReNewSpecialPassword() != null) {
            if (!dto.getNewSpecialPassword().equals(dto.getReNewSpecialPassword())) {
                errors.reject(C_RE_NEW_SPECIAL_PASSWORD_NOT_MATCH, M_RE_NEW_SPECIAL_PASSWORD_NOT_MATCH);
            } else {

                if (hasAnyWhiteSpaces(dto.getNewSpecialPassword())) {
                    dto.setNewSpecialPassword(convertAllWhiteSpacesToHash(dto.getNewSpecialPassword()));
                }

            }
        } else {
            errors.reject(C_RE_NEW_SPECIAL_PASSWORD_NULL, M_RE_NEW_SPECIAL_PASSWORD_NULL);
        }

        if (hasAnyWhiteSpaces(dto.getNewSpecialPassword())) {
            dto.setNewSpecialPassword(convertAllWhiteSpacesToHash(dto.getNewSpecialPassword()));
        }
    }

    private boolean isLoginExists(String login) {
        return adminRepo.findByLogin(login) != null;
    }

    private boolean isFieldLengthCorrect(String fieldToCheck, int minLength, int maxLength) {
        return fieldToCheck.length() < minLength || fieldToCheck.length() > maxLength;
    }

    private boolean hasAnyWhiteSpaces(String fieldToCheck) {
        for (int i = 0; i < fieldToCheck.length(); i++) {
            if (fieldToCheck.charAt(i) == ' ' || fieldToCheck.charAt(i) == '_') {
                return true;
            }
        }

        return false;
    }

    private String convertAllWhiteSpacesToHash(String fieldToConvert) {
        for (int i = 0; i < fieldToConvert.length(); i++) {
            if (fieldToConvert.charAt(i) == ' ' || fieldToConvert.charAt(i) == '_') {
                fieldToConvert = fieldToConvert.replace(' ', '#');
            }
        }

        return fieldToConvert;
    }
}
