package pl.robert.project.app.admin.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAdminPasswordDto {

    private String oldPassword;
    private String reOldPassword;
    private String newPassword;
    private String reNewPassword;

    @JsonIgnore
    long id;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();
}
