package pl.robert.project.app.transaction.domain;

import lombok.*;
import pl.robert.project.app.user_bank_account.UserBankAccount;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static pl.robert.project.app.transaction.domain.TransactionValidationStrings.*;

@Entity
@Table(name = "transactions")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Size(min = MIN_TRANSACTION_TITLE_LENGTH, max = MAX_TRANSACTION_TITLE_LENGTH)
    @Column(nullable = false)
    private String title;

    @Size(min = MIN_TRANSACTION_DESCRIPTION_LENGTH, max = MAX_TRANSACTION_DESCRIPTION_LENGTH)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "sender_bank_account_number", length = BANK_ACCOUNT_NUMBER_LENGTH, nullable = false)
    private String senderBankAccountNumber;

    @Column(name = "receiver_bank_account_number", length = BANK_ACCOUNT_NUMBER_LENGTH, nullable = false)
    private String receiverBankAccountNumber;

    @ManyToOne
    @JoinColumn(name = "pesel_user_bank_account")
    private UserBankAccount account;
}
