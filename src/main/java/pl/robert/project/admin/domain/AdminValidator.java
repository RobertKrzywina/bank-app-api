package pl.robert.project.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.admin.domain.dto.ReadAdminDto;
import pl.robert.project.admin.domain.dto.UpdateAdminDto;

@Component
@AllArgsConstructor
class AdminValidator implements Validator {

    private AdminRepository adminRepo;

    final static int COL_LENGTH_LOGIN = 18;
    final static int COL_LENGTH_PASSWORD = 36;
    final static int COL_LENGTH_SPECIAL_PASSWORD = 36;


    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.isAssignableFrom(CreateAdminDto.class)) ||
               (clazz.isAssignableFrom(ReadAdminDto.class))   ||
               (clazz.isAssignableFrom(UpdateAdminDto.class)) ||
               (clazz.isAssignableFrom(DeleteAdminDto.class));
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;




    }



    private boolean isLoginExists(String login) {
        return adminRepo.findByLogin(login) != null;
    }

    private boolean isPasswordExists(String password) {
        return adminRepo.findByPassword(password) != null;
    }
}
