package pl.robert.project.admin.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeAdminSpecialPasswordQueryDto {

    private String newSpecialPassword;
}
