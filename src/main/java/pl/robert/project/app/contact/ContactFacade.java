package pl.robert.project.app.contact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class ContactFacade {

    private ContactRepository repository;
    private ContactValidator validator;

    public void validate(Contact contact, BindingResult result) {
        validator.validate(contact, result);
    }

    public void saveUserContact(Contact contact) {
        repository.saveAndFlush(contact);
    }
}
