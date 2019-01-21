package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.app.user.domain.dto.ChangeUserPasswordDto;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.domain.dto.UserDto;

@Component
@AllArgsConstructor
class UserValidator implements Validator, UserValidationStrings {

    private UserRepository userRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.isAssignableFrom(CreateUserDto.class) || clazz.isAssignableFrom(ReadUserDto.class) ||
                clazz.isAssignableFrom(ChangeUserPasswordDto.class));
    }

    @Override
    public void validate(Object obj, Errors errors) {

        if (obj instanceof CreateUserDto) {
            CreateUserDto dto = (CreateUserDto) obj;

            validateCreateUser(dto, errors);

        } else if (obj instanceof ReadUserDto) {
            ReadUserDto dto = (ReadUserDto) obj;

            validateReadUser(dto, errors);
        }

        ((UserDto) obj).setErrors(errors.getAllErrors());
    }

    void validateGetAllUsers(ReadUserDto dto, Errors errors) {

        if (userRepo.findAll().isEmpty()) {
            errors.reject(C_USERS_NOT_EXISTS, M_USERS_NOT_EXISTS);
        }

        dto.setErrors(errors.getAllErrors());
    }

    void validateGetUser(String pesel, ReadUserDto dto, Errors errors) {

        if (userRepo.findByPesel(pesel) == null) {
            errors.reject(C_USER_NOT_EXISTS, M_USER_NOT_EXISTS);
        }

        dto.setErrors(errors.getAllErrors());
    }

    private void validateCreateUser(CreateUserDto dto, Errors errors) {

        if (dto.getPesel() != null) {

            if (isFieldLengthCorrect(dto.getPesel(), PESEL_LENGTH, PESEL_LENGTH)) {
                errors.reject(C_PESEL_LENGTH, M_PESEL_LENGTH);
            }

            if (isPeselExists(dto.getPesel()) && dto.getPesel().length() == PESEL_LENGTH) {
                errors.reject(C_USER_PESEL_EXISTS, M_USER_PESEL_EXISTS);
            }

        } else {
            errors.reject(C_PESEL_NULL, M_PESEL_NULL);
        }

        if (dto.getFirstName() != null) {

            if (isFieldLengthCorrect(dto.getFirstName(), NAME_MIN_LENGTH, NAME_MAX_LENGTH)) {
                errors.reject(C_FIRST_NAME_LENGTH, M_FIRST_NAME_LENGTH);
            }

        } else {
            errors.reject(C_FIRST_NAME_NULL, M_FIRST_NAME_NULL);
        }

        if (dto.getLastName() != null) {

            if (isFieldLengthCorrect(dto.getLastName(), NAME_MIN_LENGTH, NAME_MAX_LENGTH)) {
                errors.reject(C_LAST_NAME_LENGTH, M_LAST_NAME_LENGTH);
            }

        } else {
            errors.reject(C_LAST_NAME_NULL, M_LAST_NAME_NULL);
        }

        if (dto.getPassword() != null) {

            if (isFieldLengthCorrect(dto.getPassword(), PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH)) {
                errors.reject(C_PASSWORD_LENGTH, M_PASSWORD_LENGTH);
            }

            if (!dto.getRePassword().equals(dto.getPassword())) {
                errors.reject(C_RE_PASSWORD_NOT_MATCH_PASSWORD, M_RE_PASSWORD_NOT_MATCH_PASSWORD);
            }

        } else {
            errors.reject(C_PASSWORD_NULL, M_PASSWORD_NULL);
        }
    }

    void validateChangeUserPassword(ChangeUserPasswordDto dto, Errors errors) {

        User user = userRepo.findByPesel(dto.getPesel());

        if (user == null) {
            errors.reject(C_USER_NOT_EXISTS, M_USER_NOT_EXISTS);

        } else {

            if (dto.getOldPassword() != null) {

                if (!dto.getOldPassword().equals(user.getDecodedBCryptPassword())) {
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
                if (isFieldLengthCorrect(dto.getNewPassword(), PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH)) {
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

        dto.setErrors(errors.getAllErrors());
    }

    private void validateReadUser(ReadUserDto dto, Errors errors) {
        if (userRepo.findByPesel(dto.getPesel()) == null) {
            errors.reject(C_USER_NOT_EXISTS, M_USER_NOT_EXISTS);
        }
    }

    private boolean isFieldLengthCorrect(String fieldToCheck, int minLength, int maxLength) {
        return fieldToCheck.length() < minLength || fieldToCheck.length() > maxLength;
    }

    private boolean isPeselExists(String pesel) {
        return userRepo.findByPesel(pesel) != null;
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
