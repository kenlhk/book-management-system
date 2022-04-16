package com.kenlhk.notekeeper.dto.source;

import lombok.Data;

@Data
public class BookResponse extends SourceResponse{
    private String publisher;
    private Integer publishingYear;
}
