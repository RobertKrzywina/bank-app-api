package pl.robert.project.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AdminDto {

    protected String login;
    protected String password;
    protected String specialPassword;
}
