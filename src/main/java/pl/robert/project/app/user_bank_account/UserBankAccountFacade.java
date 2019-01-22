package pl.robert.project.app.user_bank_account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserBankAccountFacade {

    private UserBankAccountRepository repository;

    public UserBankAccount findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    public void saveUserBankAccount(UserBankAccount bankAccount) {
        repository.saveAndFlush(bankAccount);
    }

    public void addMoneyToReceivedUser(Double amount, String bankAccountNumber) {
         repository.addAmountToReceivedUser(amount, bankAccountNumber);
    }
}
