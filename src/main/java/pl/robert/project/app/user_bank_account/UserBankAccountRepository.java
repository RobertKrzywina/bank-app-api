package pl.robert.project.app.user_bank_account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

interface UserBankAccountRepository extends JpaRepository<UserBankAccount, String> {

    UserBankAccount findByAccountNumber(String accountNumber);

    @Modifying
    @Transactional
    @Query("UPDATE UserBankAccount u SET u.accountBalance = u.accountBalance + :givenMoney " +
           "WHERE u.accountNumber = :accountNumber")
    void addAmountToReceivedUser(@Param("givenMoney") Double givenMoney,
                                 @Param("accountNumber") String accountNumber);
}
