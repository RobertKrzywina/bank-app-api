package pl.robert.project.app.admin.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAdminQueryDto {

    private String name;
    private String login;
    private String password;
    private String roleName;
}
