package com.kenlhk.notekeeper.dto.note;

import com.kenlhk.notekeeper.model.Source;
import com.kenlhk.notekeeper.model.Tag;
import lombok.Data;

import java.util.Set;

@Data
public class NoteRequest {
    private String subject;
    private String content;
    private Source source;
    private Set<Tag> tags;
}
