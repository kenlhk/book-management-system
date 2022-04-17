package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.model.Tag;

public interface TagService {
    Tag addTag(Tag tag, Long noteId);

    void removeTag(Tag tag, Long noteId);
}
