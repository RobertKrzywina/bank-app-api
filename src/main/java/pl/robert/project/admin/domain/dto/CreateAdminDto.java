package pl.robert.project.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreateAdminDto extends AdminDto {

    private String rePassword;
    private String reSpecialPassword;
}
