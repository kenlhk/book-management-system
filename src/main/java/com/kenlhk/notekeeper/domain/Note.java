package com.kenlhk.notekeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_seq")
    @SequenceGenerator(name="note_id_seq", sequenceName = "note_id_seq", initialValue = 1, allocationSize = 1)
    private long id;
    private String subject;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

    @OneToOne (fetch = FetchType.EAGER)
    private Source source;

    @OneToMany (fetch = FetchType.EAGER)
    private Set<Tag> tags;

    public Note(){
        this.createdAt = LocalDateTime.now();
    }
}
