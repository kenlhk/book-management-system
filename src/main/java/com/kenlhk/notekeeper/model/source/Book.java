package com.kenlhk.notekeeper.model.source;

import com.kenlhk.notekeeper.model.Source;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
@Data
public class Book extends Source {
    @Column(name = "publicationYear")
    private Integer publicationYear;

    @Column(name = "publisher")
    private String publisher;
}
