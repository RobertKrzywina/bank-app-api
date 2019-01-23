package pl.robert.project.app.contact;

import org.springframework.data.jpa.repository.JpaRepository;

interface ContactRepository extends JpaRepository<Contact, String> {

    Contact findByEmail(String email);

    Contact findByPhoneNumber(String phoneNumber);
}
