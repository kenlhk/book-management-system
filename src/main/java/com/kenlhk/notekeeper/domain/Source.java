package com.kenlhk.notekeeper.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sources")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_id_seq")
    @SequenceGenerator(name="source_id_seq", sequenceName = "source_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;
}
