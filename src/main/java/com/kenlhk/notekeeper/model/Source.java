package com.kenlhk.notekeeper.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sources")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category")
public abstract class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_id_seq")
    @SequenceGenerator(name = "source_id_seq", sequenceName = "source_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "category", insertable = false, updatable = false)
    private String category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;
}
