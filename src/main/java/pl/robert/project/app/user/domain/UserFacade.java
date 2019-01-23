package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.security.dto.AppUserDto;
import pl.robert.project.app.transaction.domain.TransactionFacade;
import pl.robert.project.app.transaction.domain.dto.SendTransactionDto;
import pl.robert.project.app.user.domain.dto.AboutMeUserDto;
import pl.robert.project.app.user.domain.dto.ChangeUserPasswordDto;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.ReadUserDto;
import pl.robert.project.app.user.query.AboutMeUserQueryDto;
import pl.robert.project.app.user.query.BaseUserQuery;
import pl.robert.project.app.user.query.CreateUserQueryDto;
import pl.robert.project.app.user.query.ReadUserQueryDto;
import pl.robert.project.app.address.Address;
import pl.robert.project.app.address.AddressFacade;
import pl.robert.project.app.bank_account.BankAccount;
import pl.robert.project.app.bank_account.BankAccountFacade;
import pl.robert.project.app.contact.Contact;
import pl.robert.project.app.contact.ContactFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class UserFacade {

    private UserRepository repository;
    private UserFactory factory;
    private UserValidator validator;
    private BaseUserQuery baseQuery;
    private CreateUserDto createDto;
    private ReadUserDto readDto;
    private ChangeUserPasswordDto changePasswordDto;
    private AboutMeUserDto aboutMeDto;
    private ContactFacade contactFacade;
    private AddressFacade addressFacade;
    private BankAccountFacade bankAccountFacade;
    private TransactionFacade transactionFacade;

    public CreateUserQueryDto add(CreateUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            Contact contact = new Contact(dto);
            Address address = new Address(dto);

            contactFacade.validate(contact, result);
            addressFacade.validate(address, result);
            validator.validate(dto, result);

            if (!result.hasErrors()) {

                BankAccount bankAccount = new BankAccount(dto.getPesel(), bankAccountNumberGenerator());
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                createDto.setPesel(dto.getPesel());
                createDto.setFirstName(dto.getFirstName());
                createDto.setLastName(dto.getLastName());
                createDto.setPassword(dto.getPassword());
                createDto.setRePassword(dto.getRePassword());
                dto.setPassword(passwordEncoder.encode(createDto.getPassword()));

                createDto.setProvince(dto.getProvince());
                createDto.setCity(dto.getCity());
                createDto.setZipCode(dto.getZipCode());
                createDto.setStreet(dto.getStreet());
                createDto.setHouseNumber(dto.getHouseNumber());

                createDto.setEmail(dto.getEmail());
                createDto.setPhoneNumber(dto.getPhoneNumber());

                createDto.setAccountNumber(bankAccount.getAccountNumber());
                dto.setAccountNumber(bankAccount.getAccountNumber());

                contactFacade.saveUserContact(contact);
                addressFacade.saveUserAddress(address);
                bankAccountFacade.saveBankAccount(bankAccount);

                repository.saveAndFlush(factory.create(dto));

                return baseQuery.query(createDto);
            }
        }

        return null;
    }

    private String bankAccountNumberGenerator() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("PL");
        do {
            for (int i = 3; i < 30; i++) {
                if (i % 5 == 0) {
                    sb.append(" ");
                } else {
                    int num = random.nextInt(10);
                    sb.append(num);
                }
            }
        } while (isAccountNumberExists(sb.toString()));
        return sb.toString();
    }

    private boolean isAccountNumberExists(String accountNumber) {
        return bankAccountFacade.findByAccountNumber(accountNumber) != null;
    }

    public List<ReadUserQueryDto> getAll(ReadUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            List<User> users = repository.findAll();
            validator.validateGetAllUsers(dto, result);

            if (!result.hasErrors()) {
                List<ReadUserQueryDto> usersDto = new ArrayList<>();

                for (User user : users) {
                    usersDto.add(new ReadUserQueryDto(
                            user.getPesel(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getAddress().getProvince(),
                            user.getAddress().getCity(),
                            user.getAddress().getZipCode(),
                            user.getAddress().getStreet(),
                            user.getAddress().getHouseNumber(),
                            user.getContact().getEmail(),
                            user.getContact().getPhoneNumber(),
                            user.getDecodedBCryptPassword(),
                            user.getBankAccount().getAccountNumber(),
                            user.getBankAccount().getAccountBalance()
                    ));
                }

                return usersDto;
            }
        }

        return null;
    }

    public void deleteAllUsers(ReadUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validateGetAllUsers(dto, result);

            if (!result.hasErrors()) {
                repository.deleteAll();
            }
        }
    }

    public void deleteUserByPesel(String pesel, ReadUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validateGetUser(pesel, dto, result);

            if (!result.hasErrors()) {
                repository.deleteById(pesel);
            }
        }
    }

    public ReadUserQueryDto getUserByPesel(String pesel, ReadUserDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            validator.validate(dto, result);

            if (!result.hasErrors()) {

                User user = repository.findByPesel(pesel);

                readDto.setPesel(user.getPesel());
                readDto.setFirstName(user.getFirstName());
                readDto.setLastName(user.getLastName());
                readDto.setPassword(user.getDecodedBCryptPassword());
                readDto.setProvince(user.getAddress().getProvince());
                readDto.setCity(user.getAddress().getCity());
                readDto.setZipCode(user.getAddress().getZipCode());
                readDto.setStreet(user.getAddress().getStreet());
                readDto.setHouseNumber(user.getAddress().getHouseNumber());
                readDto.setEmail(user.getContact().getEmail());
                readDto.setPhoneNumber(user.getContact().getPhoneNumber());
                readDto.setAccountNumber(user.getBankAccount().getAccountNumber());
                readDto.setAccountBalance(user.getBankAccount().getAccountBalance());

                return baseQuery.query(readDto);
            }
        }

        return null;
    }

    public void changePassword(String pesel, ChangeUserPasswordDto dto, BindingResult result) {
        if (validator.supports(dto.getClass())) {

            dto.setPesel(pesel);

            validator.validateChangeUserPassword(dto, result);

            if (!result.hasErrors()) {

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                repository.updateUserPassword(passwordEncoder.encode(dto.getNewPassword()), pesel);
                repository.updateUserDecodedBCryptPassword(dto.getNewPassword(), pesel);
                changePasswordDto.setNewPassword(dto.getNewPassword());
            }
        }
    }

    public AppUserDto getAppUser(String login) {
        User user = repository.findByPesel(login);

        if (user != null) {
            return new AppUserDto(
                    user.getPesel(),
                    user.getPassword(),
                    user.getRoles()
            );
        }

        return null;
    }

    public AboutMeUserQueryDto aboutMe(Authentication auth) {
        User user = repository.findByPesel(auth.getName());

        if (user != null) {

            aboutMeDto.setPesel(user.getPesel());
            aboutMeDto.setFirstName(user.getFirstName());
            aboutMeDto.setLastName(user.getLastName());
            aboutMeDto.setProvince(user.getAddress().getProvince());
            aboutMeDto.setCity(user.getAddress().getCity());
            aboutMeDto.setZipCode(user.getAddress().getZipCode());
            aboutMeDto.setStreet(user.getAddress().getStreet());
            aboutMeDto.setHouseNumber(user.getAddress().getHouseNumber());
            aboutMeDto.setEmail(user.getContact().getEmail());
            aboutMeDto.setPhoneNumber(user.getContact().getPhoneNumber());
            aboutMeDto.setPassword(user.getDecodedBCryptPassword());
            aboutMeDto.setAccountNumber(user.getBankAccount().getAccountNumber());
            aboutMeDto.setAccountBalance(user.getBankAccount().getAccountBalance());

            return baseQuery.query(aboutMeDto);
        }

        return null;
    }

    public void sendTransaction(Authentication auth,
                                SendTransactionDto dto, BindingResult result) {
        User user = repository.findByPesel(auth.getName());

        if (user != null) {
            dto.setCurrentAccountBalance(user.getBankAccount().getAccountBalance());
            dto.setSenderBankAccountNumber(user.getBankAccount().getAccountNumber());
            dto.setPesel(user.getPesel());
            transactionFacade.sendTransaction(dto, result);

            if (dto.getErrors().isEmpty()) {
                bankAccountFacade.getMoneyFromSenderUser(dto.getAmount(), dto.getSenderBankAccountNumber());
                bankAccountFacade.addMoneyToReceivedUser(dto.getAmount(), dto.getReceiverBankAccountNumber());
            }
        }
    }
}
