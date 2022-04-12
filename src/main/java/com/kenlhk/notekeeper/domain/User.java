package com.kenlhk.notekeeper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @Column(name = "username", nullable = false, length = 64, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email
    @Column(name = "email", nullable = false, length = 64, unique = true)
    private String email;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
