package com.kenlhk.notekeeper.dto.note;

import com.kenlhk.notekeeper.domain.Source;
import com.kenlhk.notekeeper.domain.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class NoteResponse {
    private String subject;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    private Source source;
    private Set<Tag> tags;
}
