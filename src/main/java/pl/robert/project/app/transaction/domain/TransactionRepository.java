package pl.robert.project.app.transaction.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findById(long id);

    @Query(value = "SELECT * FROM transactions WHERE receiver_bank_account_number = :bankAccountNumber OR " +
                                                    "sender_bank_account_number = :bankAccountNumber", nativeQuery = true)
    List<Transaction> findAllByBankAccountNumber(@Param("bankAccountNumber") String bankAccountNumber);

    List<Transaction> findAllByReceiverBankAccountNumber(String receiverBankAccountNumber);

    List<Transaction> findAllBySenderBankAccountNumber(String senderBankAccountNumber);
}
