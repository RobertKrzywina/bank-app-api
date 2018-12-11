package pl.robert.project.admin.domain;

import lombok.AllArgsConstructor;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.query.AdminQueryDto;

@AllArgsConstructor
public class AdminFacade {

    private AdminRepository repository;
    private AdminFactory factory;
    private AdminValidator validator;

    public AdminQueryDto add(CreateAdminDto dto) {
        if (validator.supports(dto.getClass())) {

            AdminQueryDto queryDto = new AdminQueryDto();

            queryDto.setRePassword(dto.getRePassword());
            queryDto.setReSpecialPassword(dto.getReSpecialPassword());

            Admin admin = repository.saveAndFlush(factory.create(dto));

            return admin.query(queryDto);
        }

        return null;
    }
}
