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
    String C_OLD_PASSWORD_NULL = "old_password.null";
    String C_RE_OLD_PASSWORD_NULL = "re_old_password.null";
    String C_NEW_PASSWORD_NULL = "new_password.null";
    String C_RE_NEW_PASSWORD_NULL = "re_new_password.null";
    String C_MONEY_NULL = "money.null";

    String C_OLD_PASSWORD_NOT_MATCH = "old_password.not_match";
    String C_RE_OLD_PASSWORD_NOT_MATCH = "re_old_password.not_match";
    String C_RE_NEW_PASSWORD_NOT_MATCH = "re_new_password.not_match";

    String C_PESEL_LENGTH = "pesel.length";
    String C_FIRST_NAME_LENGTH = "first_name.length";
    String C_LAST_NAME_LENGTH = "last_name.length";
    String C_PASSWORD_LENGTH ="password.length";

    String C_USER_PESEL_EXISTS = "user.exists";
    String C_USERS_NOT_EXISTS = "users.not_exists";
    String C_USER_NOT_EXISTS = "user.by_pesel_not_exists";

    String C_RE_PASSWORD_NOT_MATCH_PASSWORD = "password.not_match";

    String C_MONEY_LESS_THAN_ZERO = "money.less.than.zero";
    String C_MONEY_NOT_NUMERIC_VALUE = "money.not_numeric_value";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String M_PESEL_NULL = "Enter your pesel";
    String M_FIRST_NAME_NULL = "Enter your first name";
    String M_LAST_NAME_NULL = "Enter your last name";
    String M_PASSWORD_NULL = "Enter your password";
    String M_OLD_PASSWORD_NULL = "Enter your old password";
    String M_RE_OLD_PASSWORD_NULL = "Re enter your old password";
    String M_NEW_PASSWORD_NULL = "Enter your new password";
    String M_RE_NEW_PASSWORD_NULL = "Re enter your new password";
    String M_MONEY_NULL = "Enter some numeric value";

    String M_PESEL_LENGTH = "Pesel should have 11 numbers";
    String M_FIRST_NAME_LENGTH = "First name should have from 2 to 20 characters";
    String M_LAST_NAME_LENGTH = "Last name should have from 2 to 20 characters";
    String M_PASSWORD_LENGTH ="Password should contain from 6 to 61 characters";

    String M_USER_PESEL_EXISTS = "Pesel already exists";
    String M_USERS_NOT_EXISTS = "Users not exists";
    String M_USER_NOT_EXISTS = "User pesel not exists";

    String M_RE_PASSWORD_NOT_MATCH_PASSWORD = "Passwords are not identical";
    String M_OLD_PASSWORD_NOT_MATCH = "Old password not match";
    String M_RE_OLD_PASSWORD_NOT_MATCH = "Old passwords are not identical";
    String M_RE_NEW_PASSWORD_NOT_MATCH = "New passwords are not identical";

    String M_MONEY_LESS_THAN_ZERO = "Money can't be less than zero";
    String M_MONEY_NOT_NUMERIC_VALUE = "Enter numeric value";
}
