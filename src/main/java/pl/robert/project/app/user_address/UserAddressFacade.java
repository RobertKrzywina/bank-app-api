package pl.robert.project.app.user_address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class UserAddressFacade {

    private UserAddressRepository repository;
    private UserAddressValidator validator;

    public void validate(UserAddress address, BindingResult result) {
        validator.validate(address, result);
    }

    public void saveUserAddress(UserAddress address) {
        repository.saveAndFlush(address);
    }

    public UserAddress findByPesel(String pesel) {
        return repository.findByPesel(pesel);
    }
}
