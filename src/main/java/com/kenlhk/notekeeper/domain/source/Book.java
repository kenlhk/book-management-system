package com.kenlhk.notekeeper.domain.source;

import com.kenlhk.notekeeper.domain.Source;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
@Data
public class Book extends Source {
    @Column
    private int publishedYear;
    private String publisher;
}
