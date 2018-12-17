package pl.robert.project.admin.domain.dto;

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
    protected boolean isHeadAdmin = false;

    protected List<String> errors;

    AdminDto() {
        errors = new ArrayList<>();
    }

    AdminDto(String name, String login, String password, String specialPassword) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.specialPassword = specialPassword;
    }

    AdminDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
