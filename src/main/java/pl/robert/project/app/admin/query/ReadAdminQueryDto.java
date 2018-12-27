package pl.robert.project.app.admin.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.robert.project.app.role.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class ReadAdminQueryDto {

    private long id;
    private String name;
    private Set<Role> roles = new HashSet<>();
}
