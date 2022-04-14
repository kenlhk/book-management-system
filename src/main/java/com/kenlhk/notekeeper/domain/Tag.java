package com.kenlhk.notekeeper.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tags")
@Data
public class Tag{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq")
    @SequenceGenerator(name = "tag_id_seq", sequenceName = "tag_id_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @Column(name = "tag_name", unique = true)
    private String name;
}
