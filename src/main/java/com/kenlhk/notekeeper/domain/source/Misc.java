package com.kenlhk.notekeeper.domain.source;

import com.kenlhk.notekeeper.domain.Source;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("misc")
@Data
public class Misc extends Source {
}
