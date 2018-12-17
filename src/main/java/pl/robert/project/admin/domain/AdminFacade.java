package pl.robert.project.admin.domain;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.robert.project.admin.domain.dto.CreateAdminDto;
import pl.robert.project.admin.domain.dto.DeleteAdminDto;
import pl.robert.project.admin.domain.dto.ReadAdminDto;
import pl.robert.project.admin.query.BaseQuery;
import pl.robert.project.admin.query.CreateAdminQueryDto;
import pl.robert.project.admin.query.DeleteAdminQueryDto;
import pl.robert.project.admin.query.ReadAdminQueryDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AdminFacade implements AdminValidationStrings {

    private AdminRepository repository;
    private AdminFactory factory;
    private AdminValidator validator;
    private BaseQuery baseQuery;

    private CreateAdminDto createAdminDto;
    private ReadAdminDto readAdminDto;
    private DeleteAdminDto deleteAdminDto;

    public CreateAdminQueryDto add(CreateAdminDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                createAdminDto.setName(dto.getName());
                createAdminDto.setLogin(dto.getLogin());
                createAdminDto.setPassword(dto.getPassword());
                createAdminDto.setSpecialPassword(dto.getReSpecialPassword());
                createAdminDto.setRePassword(dto.getRePassword());
                createAdminDto.setReSpecialPassword(dto.getReSpecialPassword());

                repository.saveAndFlush(factory.create(dto));
                updateAdminsId();

                return baseQuery.query(createAdminDto);
            }
        }

        return null;
    }

    public List<ReadAdminQueryDto> getAll() {
        List<Admin> admins = repository.findAll();
        List<ReadAdminQueryDto> adminsDto = new ArrayList<>();

        for (Admin admin : admins) {
            adminsDto.add(new ReadAdminQueryDto(
                    admin.getId(),
                    admin.getName()
            ));
        }

        return adminsDto;
    }

    public ReadAdminQueryDto getAdminById(ReadAdminDto dto, long id, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                Admin admin = repository.findById(id);

                readAdminDto.setId(admin.getId());
                readAdminDto.setName(admin.getName());

                return baseQuery.query(readAdminDto);
            }
        }

        return null;
    }

    public DeleteAdminQueryDto delete() {
        List<Admin> admins = repository.findAll();

        if (admins != null) {
             repository.deleteAdminsExceptHeadAdmin();
             deleteAdminDto.setMessage(DELETED_ALL_ADMINS);
        } else {
            deleteAdminDto.setMessage(NO_ADMINS);
        }

        return baseQuery.query(deleteAdminDto);
    }

    public DeleteAdminQueryDto deleteById(DeleteAdminDto dto, long id, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {
                repository.deleteById(id);
                updateAdminsId();
                deleteAdminDto.setMessage(ADMIN_DELETED);

                return baseQuery.query(deleteAdminDto);
            }
        }

        return null;
    }

    private void updateAdminsId() {
        List<Admin> admins = repository.findAll();
        long checker = 0L;
        for (Admin admin : admins) {
            if (admin.getId() != ++checker) {
                repository.updateAdminId(checker, admin.getId());
            }
        }
    }
}
