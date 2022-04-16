package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.model.Tag;

public interface TagService {
    Tag addTag(Tag tag, long noteId);

    void removeTag(Tag tag, long noteId);
}
