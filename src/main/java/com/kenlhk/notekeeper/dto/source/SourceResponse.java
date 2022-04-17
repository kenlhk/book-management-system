package com.kenlhk.notekeeper.dto.source;

import lombok.Data;

@Data
public abstract class SourceResponse {
    private Long id;
    private String category;
    private String title;
    private String author;
}
