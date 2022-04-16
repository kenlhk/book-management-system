package com.kenlhk.notekeeper.model.source;

import com.kenlhk.notekeeper.model.Source;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("misc")
@Data
public class Misc extends Source {
}
