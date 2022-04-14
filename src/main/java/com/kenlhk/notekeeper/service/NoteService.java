package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.domain.Note;
import com.kenlhk.notekeeper.domain.Tag;

import java.util.List;

public interface NoteService {
    List<Note> findAllNotes();

    Note findNoteById(long id);

    Note saveNote(Note note);

    Note updateNote(Note note, long id);

    void deleteNote(long id);

    Note addTag(Tag tag, long id);

    void removeTag(Tag tag, long id);
}
