package com.kenlhk.notekeeper.dto.source;

import lombok.Data;

@Data
public class PodcastResponse extends SourceResponse{
    private String channel;
    private String url;
}
