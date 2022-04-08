package com.kenlhk.notekeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq")
    @SequenceGenerator(name="tag_id_seq", sequenceName = "tag_id_seq", initialValue = 1, allocationSize = 1)
    private long Id;
    private String name;

}
