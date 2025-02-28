package nl.Gevliegt.Gevliegt.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Email is mandatory")
    private String email;

    private String firstName;

    private String lastName;

    @Past(message = "Birthdate must be in the past")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    private String profilePictureUrl;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is mandatory")
    private Role role;
}