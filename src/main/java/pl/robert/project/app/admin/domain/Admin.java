package pl.robert.project.app.admin.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static pl.robert.project.app.admin.domain.AdminValidator.*;

@Entity
@Table(name = "admins")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long id;

    @Size(min = MIN_LENGTH_NAME, max = MAX_LENGTH_NAME)
    @Column(nullable = false)
    private String name;

    @Size(min = MIN_LENGTH_LOGIN, max = MAX_LENGTH_LOGIN)
    @Column(nullable = false)
    private String login;

    @Size(min = MIN_LENGTH_PASSWORD, max = MAX_LENGTH_PASSWORD)
    @Column(nullable = false)
    private String password;

    @Size(min = MIN_LENGTH_SPECIAL_PASSWORD, max = MAX_LENGTH_SPECIAL_PASSWORD)
    @Column(nullable = false, name = "special_password")
    private String specialPassword;

    @Column(nullable = false, name = "is_head_admin")
    private boolean isHeadAdmin = false;
}
