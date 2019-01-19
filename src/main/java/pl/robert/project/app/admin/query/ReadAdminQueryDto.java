package pl.robert.project.app.admin.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadAdminQueryDto {

    private long id;
    private String name;
    private String roleName;
}
