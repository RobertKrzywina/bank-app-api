package pl.robert.project.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByLogin(String login);
    Admin findByPassword(String password);
}
