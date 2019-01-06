package pl.robert.project.app.user_bank_account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import static pl.robert.project.app.user_bank_account.UserBankAccountValidationStrings.MAX_LENGTH_ACCOUNT_NUMBER;
import static pl.robert.project.app.user_bank_account.UserBankAccountValidationStrings.MIN_LENGTH_ACCOUNT_NUMBER;

@Entity
@Table(name = "user_bank_account")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBankAccount {

    @Id
    @Column(name = "user_bank_account_pesel", unique = true, nullable = false)
    private String pesel;

    @Size(min = MIN_LENGTH_ACCOUNT_NUMBER, max = MAX_LENGTH_ACCOUNT_NUMBER)
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column
    private double balance;
}
