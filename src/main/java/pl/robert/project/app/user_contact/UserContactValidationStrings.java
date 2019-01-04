package pl.robert.project.app.user_contact;

interface UserContactValidationStrings {

    int PHONE_NUMBER_LENGTH = 9;
    String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    String PHONE_NUMBER_REGEX = "^((?!(0))[0-9]{9})$";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String C_EMAIL_NULL = "email.null";
    String C_PHONE_NUMBER_NULL = "phone_number.null";

    String C_PHONE_NUMBER_LENGTH = "phone_number.length";

    String C_EMAIL_EXISTS = "email.exists";
    String C_PHONE_NUMBER_EXISTS = "phone_number.exists";

    String C_EMAIL_INVALID = "email.invalid";
    String C_PHONE_NUMBER_INVALID = "phone_number.invalid";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String M_EMAIL_NULL = "Enter your email";
    String M_PHONE_NUMBER_NULL = "Enter your phone number";

    String M_PHONE_NUMBER_LENGTH = "Phone number should have 9 digits";

    String M_EMAIL_EXISTS = "Email already exists";
    String M_PHONE_NUMBER_EXISTS = "Phone number already exists";

    String M_EMAIL_INVALID = "You have entered an invalid email address";
    String M_PHONE_NUMBER_INVALID = "You have entered an invalid phone number";
}
