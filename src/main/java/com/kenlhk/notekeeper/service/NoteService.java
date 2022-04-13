package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.domain.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAllNotes();

    Note findNoteById(Long id);

    Note saveNote(Note note);

    Note updateNote(Note note, long id);

    void deleteNote(long id);
}
