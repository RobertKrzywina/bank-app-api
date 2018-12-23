package pl.robert.project.app.admin.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public abstract class AdminDto {

    protected long id;
    protected String name;
    protected String login;
    protected String password;
    protected String specialPassword;
    protected boolean isHeadAdmin;

    protected List<String> errors;

    AdminDto() {
        errors = new ArrayList<>();
        isHeadAdmin = false;
    }
}
