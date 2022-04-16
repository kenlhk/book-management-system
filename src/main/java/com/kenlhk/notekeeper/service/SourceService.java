package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.model.Source;

public interface SourceService {
    Source addSource(Source source, long noteId);

    void removeSource(long id);
}
