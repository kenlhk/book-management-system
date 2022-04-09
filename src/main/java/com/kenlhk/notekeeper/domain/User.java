package com.kenlhk.notekeeper.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @Column(nullable = false, length = 64, unique = true)
    @NonNull
    private String email;

    @Column(nullable = false, length = 64)
    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
