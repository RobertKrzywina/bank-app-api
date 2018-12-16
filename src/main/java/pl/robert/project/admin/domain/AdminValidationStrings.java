package pl.robert.project.admin.domain;

interface AdminValidationStrings {

    int MIN_LENGTH_LOGIN = 3;
    int MAX_LENGTH_LOGIN = 18;
    int MIN_LENGTH_PASSWORD = 6;
    int MAX_LENGTH_PASSWORD = 36;
    int MIN_LENGTH_SPECIAL_PASSWORD = 6;
    int MAX_LENGTH_SPECIAL_PASSWORD = 36;

    String LOGIN_LENGTH = "login.length";
    String LOGIN_EXISTS = "admin.exists";
    String LOGIN_NOT_EXISTS = "login.not_exits";
    String LOGIN_NULL = "login.null";
    String PASSWORD_LENGTH = "admin.password_length";
    String PASSWORD_NULL = "password.null";
    String SPECIAL_PASSWORD_LENGTH = "admin.special_password_length";
    String SPECIAL_PASSWORD_NULL = "special_password.null";
    String RE_PASSWORD_NOT_MATCH = "password.not_match";
    String RE_SPECIAL_PASSWORD_NOT_MATCH = "special_password.not_match";

    String WRONG_LOGIN_LENGTH = "Login should contain from 3 to 18 characters";
    String WRONG_ADMIN_LOGIN = "Login already exists";
    String ADMIN_ID_NOT_EXISTS = "Admin id not exists";
    String LOGIN_REQUIRED = "Enter your login";
    String WRONG_PASSWORD_LENGTH = "Password should contain from 6 to 36 characters";
    String PASSWORD_REQUIRED = "Enter your password";
    String WRONG_SPECIAL_PASSWORD_LENGTH = "Special password should contain from 6 to 36 characters";
    String SPECIAL_PASSWORD_REQUIRED = "Enter your special password";
    String RE_PASSWORD_NOT_MATCH_PASSWORD = "Passwords are not identical";
    String RE_SPECIAL_PASSWORD_NOT_MATCH_PASSWORD = "Special passwords are not identical";
}
