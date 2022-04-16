package com.kenlhk.notekeeper.mapper;

import com.kenlhk.notekeeper.dto.tag.TagRequest;
import com.kenlhk.notekeeper.dto.tag.TagResponse;
import com.kenlhk.notekeeper.model.Tag;
import com.kenlhk.notekeeper.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagMapper {
    private final CommonMapper commonMapper;
    private final TagService tagService;

    public TagResponse addTag(TagRequest request, long noteId) {
        Tag tag = commonMapper.map(request, Tag.class);
        return commonMapper.map(tagService.addTag(tag, noteId), TagResponse.class);
    }

    public void removeTag(TagRequest request, long noteId) {
        Tag tag = commonMapper.map(request, Tag.class);
        tagService.removeTag(tag, noteId);
    }
}
