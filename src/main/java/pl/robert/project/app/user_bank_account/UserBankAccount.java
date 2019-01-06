package pl.robert.project.app.user_bank_account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column
    private double balance = 0.0;
}
