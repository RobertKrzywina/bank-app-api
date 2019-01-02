package pl.robert.project.app.admin.domain;

interface AdminValidationStrings {

    int MIN_LENGTH_NAME = 2;
    int MAX_LENGTH_NAME = 20;
    int MIN_LENGTH_LOGIN = 3;
    int MAX_LENGTH_LOGIN = 18;
    int MIN_LENGTH_PASSWORD = 6;
    int MAX_LENGTH_PASSWORD = 61;

    ////////////////////////////////////////////////////////////////////////////////////////////

    String ROLE_HEAD_ADMIN = "HEAD-ADMIN";
    String ROLE_ADMIN = "ADMIN";
    String ROLE_USER = "USER";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String C_NAME_NULL = "name.null";
    String C_LOGIN_NULL = "login.null";
    String C_PASSWORD_NULL = "password.null";
    String C_OLD_PASSWORD_NULL = "old_password.null";
    String C_RE_OLD_PASSWORD_NULL = "re_old_password.null";
    String C_NEW_PASSWORD_NULL = "new_password.null";
    String C_RE_NEW_PASSWORD_NULL = "re_new_password.null";
    String C_ROLE_NAME_NULL = "role_name.null";

    String C_OLD_PASSWORD_NOT_MATCH = "old_password.not_match";
    String C_RE_PASSWORD_NOT_MATCH = "password.not_match";
    String C_RE_OLD_PASSWORD_NOT_MATCH = "re_old_password.not_match";
    String C_RE_NEW_PASSWORD_NOT_MATCH = "re_new_password.not_match";

    String C_NAME_LENGTH = "name.length";
    String C_LOGIN_LENGTH = "login.length";
    String C_PASSWORD_LENGTH = "admin.password_length";

    String C_ADMIN_NOT_EXISTS = "admin.by_id_not_exists";
    String C_ADMIN_LOGIN_EXISTS = "admin.exists";
    String C_CANT_DELETE_HEAD_ADMIN = "cant.delete.head_admin";
    String C_INCORRECT_ROLE_NAME = "incorrect.role_name";

    ////////////////////////////////////////////////////////////////////////////////////////////

    String M_NAME_NULL = "Enter your name";
    String M_LOGIN_NULL = "Enter your login";
    String M_PASSWORD_NULL = "Enter your password";
    String M_OLD_PASSWORD_NULL = "Enter your old password";
    String M_RE_OLD_PASSWORD_NULL = "Re enter your old password";
    String M_NEW_PASSWORD_NULL = "Enter your new password";
    String M_RE_NEW_PASSWORD_NULL = "Re enter your new password";
    String M_ROLE_NAME_NULL = "Enter your role name";

    String M_NAME_LENGTH = "Name should have from 2 to 20 characters";
    String M_LOGIN_LENGTH = "Login should contain from 3 to 18 characters";
    String M_PASSWORD_LENGTH = "Password should contain from 6 to 61 characters";

    String M_ADMIN_LOGIN_EXISTS = "Login already exists";
    String M_ADMIN_ID_NOT_EXISTS = "Admin id not exists";

    String M_RE_PASSWORD_NOT_MATCH_PASSWORD = "Passwords are not identical";
    String M_OLD_PASSWORD_NOT_MATCH = "Old password not match";
    String M_RE_OLD_PASSWORD_NOT_MATCH = "Old passwords are not identical";
    String M_RE_NEW_PASSWORD_NOT_MATCH = "New passwords are not identical";

    String M_DELETED_ALL_ADMINS = "All admins deleted successfully";
    String M_NO_ADMINS = "No admins here";
    String M_CANT_DELETE_HEAD_ADMIN = "You can't delete head admin";
    String M_ADMIN_DELETED = "Admin deleted";
    String M_INCORRECT_ROLE_NAME = "Incorrect role name";
}
