package com.kenlhk.notekeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_id_seq")
    @SequenceGenerator(name="source_id_seq", sequenceName = "source_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String title;
    private String author;

}
