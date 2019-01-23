package pl.robert.project.app.bank_account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.app.transaction.domain.Transaction;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

import static pl.robert.project.app.bank_account.BankAccountValidationStrings.ACCOUNT_NUMBER_LENGTH;

@Entity
@Table(name = "bank_accounts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @JsonIgnore
    @Column(name = "bank_account_owner_pesel", unique = true, nullable = false)
    private String pesel;

    @Column(name = "account_number", unique = true, nullable = false, length = ACCOUNT_NUMBER_LENGTH)
    private String accountNumber;

    @Column(name = "account_balance")
    private double accountBalance = 0.0;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions = new LinkedList<>();

    public BankAccount(String pesel, String accountNumber) {
        this.pesel = pesel;
        this.accountNumber = accountNumber;
    }

    public BankAccount(CreateUserDto dto) {
        pesel = dto.getPesel();
        accountNumber = dto.getAccountNumber();
    }

    public BankAccount(String pesel) {
        this.pesel = pesel;
    }
}
