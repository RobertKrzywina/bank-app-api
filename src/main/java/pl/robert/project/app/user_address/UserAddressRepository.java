package pl.robert.project.app.user_address;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
