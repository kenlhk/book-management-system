package com.kenlhk.notekeeper.domain.source;

import com.kenlhk.notekeeper.domain.Source;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
@Data
public class Podcast extends Source {
    private String channel;
    private String url;
}
