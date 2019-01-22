package pl.robert.project.app.transaction.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static pl.robert.project.app.transaction.domain.TransactionValidationStrings.BANK_ACCOUNT_NUMBER_LENGTH;
import static pl.robert.project.app.transaction.domain.TransactionValidationStrings.TRANSACTION_TITLE_LENGTH;

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

    @Temporal(TemporalType.DATE)
    @Column()
    private Date date;

    @Column(length = TRANSACTION_TITLE_LENGTH, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "sender_bank_account_number", length = BANK_ACCOUNT_NUMBER_LENGTH, nullable = false)
    private String senderBankAccountNumber;

    @Column(name = "receiver_bank_account_number", length = BANK_ACCOUNT_NUMBER_LENGTH, nullable = false)
    private String receiverBankAccountNumber;
}
