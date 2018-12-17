package pl.robert.project.admin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@NoArgsConstructor
public class ReadAdminDto extends AdminDto {

    public ReadAdminDto(long id, String name) {
        super(id, name);
    }
}
