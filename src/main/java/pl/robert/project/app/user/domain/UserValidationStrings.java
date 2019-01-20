package pl.robert.project.app.user.domain;

interface UserValidationStrings {

    int PESEL_LENGTH = 11;
    int NAME_MIN_LENGTH = 2;
    int NAME_MAX_LENGTH = 20;
    int PASSWORD_MIN_LENGTH = 6;
    int PASSWORD_MAX_LENGTH = 61;

    ////////////////////////////////////////////////////////////////////////////////////////////

    String C_PESEL_NULL = "pesel.null";
    String C_FIRST_NAME_NULL = "first_name.null";
    String C_LAST_NAME_NULL = "last_name.null";
    String C_PASSWORD_NULL = "password.null";

    String C_PESEL_LENGTH = "pesel.length";
    String C_FIRST_NAME_LENGTH = "first_name.length";
    String C_LAST_NAME_LENGTH = "last_name.length";
    String C_PASSWORD_LENGTH ="password.length";

    String C_USER_PESEL_EXISTS = "user.exists";
    String C_USERS_NOT_EXISTS = "users.not_exists";
    String C_USER_NOT_EXISTS = "user.by_pesel_not_exists";

    String C_RE_PASSWORD_NOT_MATCH_PASSWORD = "password.not_match";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String M_PESEL_NULL = "Enter your pesel";
    String M_FIRST_NAME_NULL = "Enter your first name";
    String M_LAST_NAME_NULL = "Enter your last name";
    String M_PASSWORD_NULL = "Enter your password";

    String M_PESEL_LENGTH = "Pesel should have 11 numbers";
    String M_FIRST_NAME_LENGTH = "First name should have from 2 to 20 characters";
    String M_LAST_NAME_LENGTH = "Last name should have from 2 to 20 characters";
    String M_PASSWORD_LENGTH ="Password should contain from 6 to 61 characters";

    String M_USER_PESEL_EXISTS = "Pesel already exists";
    String M_USERS_NOT_EXISTS = "Users not exists";
    String M_USER_NOT_EXISTS = "User pesel not exists";

    String M_RE_PASSWORD_NOT_MATCH_PASSWORD = "Passwords are not identical";
}
