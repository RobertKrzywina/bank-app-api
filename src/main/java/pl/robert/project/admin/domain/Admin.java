package pl.robert.project.admin.domain;

import lombok.*;
import pl.robert.project.admin.query.AdminQueryDto;

import javax.persistence.*;

import static pl.robert.project.admin.domain.AdminValidator.*;

@Entity
@Table(name = "admins")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = COL_LENGTH_LOGIN, nullable = false)
    private String login;

    @Column(length = COL_LENGTH_PASSWORD, nullable = false)
    private String password;

    @Column(length = COL_LENGTH_SPECIAL_PASSWORD, nullable = false, name = "special_password")
    private String specialPassword;

    AdminQueryDto query(AdminQueryDto dto) {
        return new AdminQueryDto(login, password, specialPassword, dto.getRePassword(), dto.getReSpecialPassword());
    }
}
