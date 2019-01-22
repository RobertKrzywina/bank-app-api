package pl.robert.project.app.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static pl.robert.project.app.transaction.domain.TransactionValidationStrings.BANK_ACCOUNT_NUMBER_LENGTH;

@Entity
@Table(name = "transactions")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "sender_bank_account_number", length = BANK_ACCOUNT_NUMBER_LENGTH, nullable = false)
    private String senderBankAccountNumber;

    @Column(name = "receiver_bank_account_number", length = BANK_ACCOUNT_NUMBER_LENGTH, nullable = false)
    private String receiverBankAccountNumber;
}
