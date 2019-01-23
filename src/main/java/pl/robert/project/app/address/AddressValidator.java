package pl.robert.project.app.address;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
class AddressValidator implements Validator, AddressValidationStrings {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Address.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Address address = (Address) obj;

        validateAddress(address, errors);

        ((Address) obj).setErrors(errors.getAllErrors());
    }

    private void validateAddress(Address address, Errors errors) {

        if (address.getProvince() == null) {
            errors.reject(C_PROVINCE_NULL, M_PROVINCE_NULL);
        }

        if (address.getCity() != null) {

            if (isFieldLengthCorrect(address.getCity(), CITY_MIN_LENGTH, CITY_MAX_LENGTH)) {
                errors.reject(C_CITY_LENGTH, M_CITY_LENGTH);
            }

        } else {
            errors.reject(C_CITY_NULL, M_CITY_NULL);
        }

        if (address.getZipCode() != null) {

            if (!isZipCodeValid(address.getZipCode())) {
                errors.reject(C_ZIP_CODE_INVALID, M_ZIP_CODE_INVALID);
            }

        } else {
            errors.reject(C_ZIP_CODE_NULL, M_ZIP_CODE_NULL);
        }

        if (address.getStreet() != null) {

            if (isFieldLengthCorrect(address.getCity(), STREET_MIN_LENGTH, STREET_MAX_LENGTH)) {
                errors.reject(C_STREET_LENGTH, M_STREET_LENGTH);
            }

        } else {
            errors.reject(C_STREET_NULL, M_STREET_NULL);
        }

        if (address.getHouseNumber() != null) {

            if (!isHouseNumberValid(address.getHouseNumber())) {
                errors.reject(C_HOUSE_NUMBER_INVALID, M_HOUSE_NUMBER_INVALID);
            }

        } else {
            errors.reject(C_HOUSE_NUMBER_NULL, M_HOUSE_NUMBER_NULL);
        }
    }

    private boolean isFieldLengthCorrect(String fieldToCheck, int minLength, int maxLength) {
        return fieldToCheck.length() < minLength || fieldToCheck.length() > maxLength;
    }

    private boolean isZipCodeValid(String zipCode) {
        return Pattern.matches(ZIP_CODE_REGEX, zipCode);
    }

    private boolean isHouseNumberValid(String houseNumber) {
        return Pattern.matches(HOUSE_NUMBER_REGEX, houseNumber);
    }
}
