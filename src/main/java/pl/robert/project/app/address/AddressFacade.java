package pl.robert.project.app.address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class AddressFacade {

    private AddressRepository repository;
    private AddressValidator validator;

    public void validate(Address address, BindingResult result) {
        validator.validate(address, result);
    }

    public void saveUserAddress(Address address) {
        repository.saveAndFlush(address);
    }
}
