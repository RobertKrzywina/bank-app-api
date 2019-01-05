package pl.robert.project.app.user_contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonIgnore
    @Column(name = "user_contact_pesel")
    private String pesel;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Transient
    @JsonIgnore
    private List<String> errors = new ArrayList<>();
}
