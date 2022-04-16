package com.kenlhk.notekeeper.dto.note;

import com.kenlhk.notekeeper.model.Source;
import com.kenlhk.notekeeper.model.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class NoteResponse {
    private long id;
    private String subject;
    private String content;
    private Source source;
    private Set<Tag> tags;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
}
