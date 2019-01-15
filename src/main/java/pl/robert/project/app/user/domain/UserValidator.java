package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.DeleteUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.domain.dto.UserDto;

@Component
@AllArgsConstructor
class UserValidator implements Validator, UserValidationStrings {

    private UserRepository userRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.isAssignableFrom(CreateUserDto.class) ||
                clazz.isAssignableFrom(ReadUserDto.class) ||
                clazz.isAssignableFrom(DeleteUserDto.class));
    }

    @Override
    public void validate(Object obj, Errors errors) {

        if (obj instanceof CreateUserDto) {
            CreateUserDto dto = (CreateUserDto) obj;

            validateCreateUser(dto, errors);

            ((CreateUserDto) obj).setErrors(errors.getAllErrors());
        } else {

            if (obj instanceof ReadUserDto) {
                ReadUserDto dto = (ReadUserDto) obj;

                validateReadUser(dto, errors);
            } else if (obj instanceof DeleteUserDto) {
                DeleteUserDto dto = (DeleteUserDto) obj;

                validateDeleteUser(dto, errors);
            }

            ((UserDto) obj).setErrors(errors.getAllErrors());
        }
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

    private void validateReadUser(ReadUserDto dto, Errors errors) {
        if (userRepo.findByPesel(dto.getPesel()) == null) {
            errors.reject(C_USER_NOT_EXISTS, M_USER_NOT_EXISTS);
        }
    }

    private void validateDeleteUser(DeleteUserDto dto, Errors errors) {
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
}
