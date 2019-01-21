package pl.robert.project.app.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

interface UserRepository extends JpaRepository<User, String> {

    User findByPesel(String pesel);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.pesel = :targetPesel")
    void updateUserPassword(@Param("newPassword") String newPassword, @Param("targetPesel") String pesel);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.decodedBCryptPassword = :newDecodedBCryptPassword WHERE u.id = :targetPesel")
    void updateUserDecodedBCryptPassword(@Param("newDecodedBCryptPassword") String newPassword,
                                         @Param("targetPesel") String pesel);
}
