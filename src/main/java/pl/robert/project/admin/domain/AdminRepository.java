package pl.robert.project.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByLogin(String login);
    Admin findById(long id);

    // query for delete admins, except head admin
    @Modifying
    @Transactional
    @Query("DELETE FROM Admin WHERE id NOT IN (1)")
    void deleteAdminsExceptHeadAdmin();

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.id = :newId WHERE a.id = :oldId")
    void updateAdminId(@Param("newId") Long newId, @Param("oldId") Long oldId);

}
