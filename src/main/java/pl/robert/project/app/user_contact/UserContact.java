package pl.robert.project.app.user_contact;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_contact")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContact {

    @Id
    @Column(name = "user_contact_pesel")
    private String pesel;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Transient
    private List<String> errors = new ArrayList<>();
}
