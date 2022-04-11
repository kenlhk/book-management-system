package com.kenlhk.notekeeper.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "notes")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_seq")
    @SequenceGenerator(name="note_id_seq", sequenceName = "note_id_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @Column(name = "subject", length = 256)
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "created_time")
    private LocalDateTime createdAt;

    @Column(name = "edited_time")
    private LocalDateTime editedAt;

    @OneToOne (fetch = FetchType.EAGER)
    private Source source;

    @OneToMany (fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @OneToOne (fetch = FetchType.EAGER)
    private User user;

    public Note(){
        this.createdAt = LocalDateTime.now();
    }
}
