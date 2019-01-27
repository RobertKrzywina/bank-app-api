package pl.robert.project.app.bank_account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    BankAccount findByAccountNumber(String accountNumber);

    BankAccount findByPesel(String pesel);

    @Query("SELECT b.accountBalance FROM BankAccount b WHERE b.accountNumber = :accountNumber")
    Double findAccountBalanceByAccountNumber(@Param("accountNumber") String accountNumber);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.accountBalance = b.accountBalance - :givenMoney " +
           "WHERE b.accountNumber = :accountNumber")
    void getMoneyFromSenderUser(@Param("givenMoney") Double givenMoney,
                                @Param("accountNumber") String accountNumber);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.accountBalance = b.accountBalance + :givenMoney " +
           "WHERE b.accountNumber = :accountNumber")
    void addAmountToReceivedUser(@Param("givenMoney") Double givenMoney,
                                 @Param("accountNumber") String accountNumber);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.accountBalance = b.accountBalance + :money " +
           "WHERE b.pesel = :pesel")
    void addMoneyToUser(@Param("money") Double money, @Param("pesel") String pesel);
}
