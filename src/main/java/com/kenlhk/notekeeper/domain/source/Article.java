package com.kenlhk.notekeeper.domain.source;

import com.kenlhk.notekeeper.domain.Source;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("article")
@Data
public class Article extends Source {
    @Column(name = "url")
    private String url;
}
