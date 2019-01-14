package pl.robert.project.app.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByLogin(String login);

    Admin findById(long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Admin WHERE roleName <> 'ROLE_HEAD-ADMIN'")
    void deleteAdminsExceptHeadAdmin();

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.password = :newPassword WHERE a.id = :targetId")
    void updateAdminPassword(@Param("newPassword") String newPassword, @Param("targetId") long id);
}
