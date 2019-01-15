package pl.robert.project.app.user_contact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@AllArgsConstructor
class UserContactValidator implements Validator, UserContactValidationStrings {

    private UserContactRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserContact.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserContact contact = (UserContact) obj;

        validateContact(contact, errors);

        ((UserContact) obj).setErrors(errors.getAllErrors());
    }

    private void validateContact(UserContact contact, Errors errors) {

        if (contact.getEmail() != null) {

            if (isEmailExists(contact.getEmail())) {
                errors.reject(C_EMAIL_EXISTS, M_EMAIL_EXISTS);
            }

            if (!isEmailValid(contact.getEmail())) {
                errors.reject(C_EMAIL_INVALID, M_EMAIL_INVALID);
            }

        } else {
            errors.reject(C_EMAIL_NULL, M_EMAIL_NULL);
        }

        if (contact.getPhoneNumber() != null) {

            if (contact.getPhoneNumber().length() != PHONE_NUMBER_LENGTH) {
                errors.reject(C_PHONE_NUMBER_LENGTH, M_PHONE_NUMBER_LENGTH);
            }

            if (isPhoneNumberExists(contact.getPhoneNumber())) {
                errors.reject(C_PHONE_NUMBER_EXISTS, M_PHONE_NUMBER_EXISTS);
            }

            if (!isPhoneNumberValid(contact.getPhoneNumber())) {
                errors.reject(C_PHONE_NUMBER_INVALID, M_PHONE_NUMBER_INVALID);
            }

        } else {
            errors.reject(C_PHONE_NUMBER_NULL, M_PHONE_NUMBER_NULL);
        }
    }

    private boolean isEmailExists(String email) {
        return repository.findByEmail(email) != null;
    }

    private boolean isPhoneNumberExists(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber) != null;
    }

    private boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
    }
}
