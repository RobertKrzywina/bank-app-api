package pl.robert.project.app.user_bank_account;

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

import static pl.robert.project.app.user_bank_account.UserBankAccountValidationStrings.ACCOUNT_NUMBER_LENGTH;

@Entity
@Table(name = "user_bank_account")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBankAccount {

    @Id
    @JsonIgnore
    @Column(name = "user_bank_account_pesel", unique = true, nullable = false)
    private String pesel;

    @Column(name = "account_number", unique = true, nullable = false, length = ACCOUNT_NUMBER_LENGTH)
    private String accountNumber;

    @Column(name = "account_balance")
    private double accountBalance = 0.0;

    @OneToMany
    @JoinColumn(name = "pesel_user_bank_account", referencedColumnName = "user_bank_account_pesel")
    private List<Transaction> transactions = new LinkedList<>();

    public UserBankAccount(String pesel, String accountNumber) {
        this.pesel = pesel;
        this.accountNumber = accountNumber;
    }

    public UserBankAccount(CreateUserDto dto) {
        pesel = dto.getPesel();
        accountNumber = dto.getAccountNumber();
    }
}
