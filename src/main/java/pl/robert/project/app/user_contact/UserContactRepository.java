package pl.robert.project.app.user_contact;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserContactRepository extends JpaRepository<UserContact, Long> {

    UserContact findByEmail(String email);
    UserContact findByPhoneNumber(String phoneNumber);
}
