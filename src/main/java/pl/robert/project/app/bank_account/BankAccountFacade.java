package pl.robert.project.app.bank_account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountFacade {

    private BankAccountRepository repository;

    public BankAccount findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    public void saveBankAccount(BankAccount bankAccount) {
        repository.saveAndFlush(bankAccount);
    }

    public void getMoneyFromSenderUser(Double amount, String bankAccountNumber) {
        repository.getMoneyFromSenderUser(amount, bankAccountNumber);
    }

    public void addMoneyToReceivedUser(Double amount, String bankAccountNumber) {
        repository.addAmountToReceivedUser(amount, bankAccountNumber);
    }

    public Double getAccountBalanceFromGivenAccountNumber(String bankAccountNumber) {
        return repository.findAccountBalanceByAccountNumber(bankAccountNumber);
    }
}
