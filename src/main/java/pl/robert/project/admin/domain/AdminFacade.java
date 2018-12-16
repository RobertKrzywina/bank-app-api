package pl.robert.project.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.query.CreateAdminQueryDto;

@AllArgsConstructor
public class AdminFacade {

    private AdminRepository repository;
    private AdminFactory factory;
    private AdminValidator validator;

    private CreateAdminDto createAdminDto;

    public CreateAdminQueryDto add(CreateAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                createAdminDto.setLogin(dto.getLogin());
                createAdminDto.setPassword(dto.getPassword());
                createAdminDto.setSpecialPassword(dto.getReSpecialPassword());
                createAdminDto.setRePassword(dto.getRePassword());
                createAdminDto.setReSpecialPassword(dto.getReSpecialPassword());

                repository.saveAndFlush(factory.create(dto));

                return factory.query(createAdminDto);
            }
        }

        return null;
    }
}
