package pl.robert.project.app.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByLogin(String login);

    Admin findById(long id);

    @Query(value = "SELECT * FROM admins WHERE role_name = 'ROLE_ADMIN'", nativeQuery = true)
    List<Admin> findAllAdminsExceptHeadAdmins();

    @Transactional
    @Query("SELECT a FROM Admin a WHERE a.id = :id AND a.roleName <> 'ROLE_HEAD_ADMIN'")
    Admin findByIdExceptHeadAdmin(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Admin WHERE roleName <> 'ROLE_HEAD-ADMIN'")
    void deleteAdminsExceptHeadAdmins();

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.password = :newPassword WHERE a.id = :targetId")
    void updateAdminPassword(@Param("newPassword") String newPassword, @Param("targetId") long id);

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.decodedBCryptPassword = :newDecodedBCryptPassword WHERE a.id = :targetId")
    void updateAdminDecodedBCryptPassword(@Param("newDecodedBCryptPassword") String newPassword, @Param("targetId") long id);
}
