package pl.robert.project.app.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class AddMoneyUserDto {

    private String money;

    @JsonIgnore
    String pesel;

    @JsonIgnore
    private List<ObjectError> errors = new ArrayList<>();
}
