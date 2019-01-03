package pl.robert.project.app.thymeleaf;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
class Controller {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
