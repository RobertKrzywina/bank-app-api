package pl.robert.project.app.thymeleaf;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/admin-panel")
    public String adminPanel() {
        return "admin-panel";
    }

    @RequestMapping("/user-panel")
    public String userPanel() {
        return "user-panel";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping("/logout-success")
    public String logoutSuccess() {
        return "logout-success";
    }
}
