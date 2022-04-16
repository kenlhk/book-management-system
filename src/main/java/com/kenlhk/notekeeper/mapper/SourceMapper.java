package com.kenlhk.notekeeper.mapper;

import com.kenlhk.notekeeper.dto.source.*;
import com.kenlhk.notekeeper.exception.ApiRequestException;
import com.kenlhk.notekeeper.model.Source;
import com.kenlhk.notekeeper.model.source.*;
import com.kenlhk.notekeeper.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SourceMapper {
    private final SourceService sourceService;
    private final CommonMapper commonMapper;

    public SourceResponse addSource(SourceRequest request, long noteId) {
        Source source;
        switch (request.getCategory()) {
            case "article":
                source = commonMapper.map(request, Article.class);
                return commonMapper.map(sourceService.addSource(source, noteId), ArticleResponse.class);
            case "book":
                source = commonMapper.map(request, Book.class);
                return commonMapper.map(sourceService.addSource(source, noteId), BookResponse.class);
            case "podcast":
                source = commonMapper.map(request, Podcast.class);
                return commonMapper.map(sourceService.addSource(source, noteId), PodcastResponse.class);
            case "video":
                source = commonMapper.map(request, Video.class);
                return commonMapper.map(sourceService.addSource(source, noteId), VideoResponse.class);
            case "misc":
                source = commonMapper.map(request, Misc.class);
                return commonMapper.map(sourceService.addSource(source, noteId), MiscResponse.class);
            default:
                throw new ApiRequestException("Category not found", HttpStatus.NOT_FOUND);
        }
    }
}
