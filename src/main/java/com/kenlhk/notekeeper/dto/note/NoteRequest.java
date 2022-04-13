package com.kenlhk.notekeeper.dto.note;

import com.kenlhk.notekeeper.domain.Source;
import com.kenlhk.notekeeper.domain.Tag;
import lombok.Data;

import java.util.Set;

@Data
public class NoteRequest {
    private String subject;
    private String content;
    private Source source;
    private Set<Tag> tags;
}
