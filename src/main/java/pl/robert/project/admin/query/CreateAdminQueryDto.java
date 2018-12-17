package pl.robert.project.admin.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAdminQueryDto {

    private String name;
    private String login;
    private String password;
    private String specialPassword;
    private String rePassword;
    private String reSpecialPassword;
}
