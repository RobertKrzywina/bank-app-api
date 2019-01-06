package pl.robert.project.app.user_bank_account;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserBankAccountRepository extends JpaRepository<UserBankAccount, Long> {

    UserBankAccount findByAccountNumber(String accountNumber);

    UserBankAccount findByPesel(String pesel);
}
