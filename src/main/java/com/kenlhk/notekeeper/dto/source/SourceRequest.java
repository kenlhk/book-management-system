package com.kenlhk.notekeeper.dto.source;

import lombok.Data;

@Data
public class SourceRequest {
    private String category;
    private String title;
    private String author;
    private String url;
    private String publisher;
    private Integer publicationYear;
    private String channel;
}
