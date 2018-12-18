package pl.robert.project.admin.domain;

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
    @Query("DELETE FROM Admin WHERE isHeadAdmin NOT IN (true)")
    void deleteAdminsExceptHeadAdmin();

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.id = :newId WHERE a.id = :oldId")
    void updateAdminId(@Param("newId") Long newId, @Param("oldId") Long oldId);

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.password = :newPassword WHERE a.id = :targetId")
    void updateAdminPassword(@Param("newPassword") String newPassword, @Param("targetId") long id);

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.specialPassword = :newSpecialPassword WHERE a.id = :targetId")
    void updateAdminSpecialPassword(@Param("newSpecialPassword") String newSpecialPassword, @Param("targetId") long id);
}
