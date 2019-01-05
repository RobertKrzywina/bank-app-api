package pl.robert.project.app.user_contact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class UserContactFacade {

    private UserContactRepository repository;
    private UserContactValidator validator;

    public void validate(UserContact contact, BindingResult result) {
        validator.validate(contact, result);
    }

    public void saveUserContact(UserContact contact) {
        repository.saveAndFlush(contact);
    }

    public UserContact findByPesel(String pesel) {
        return repository.findByPesel(pesel);
    }
}
