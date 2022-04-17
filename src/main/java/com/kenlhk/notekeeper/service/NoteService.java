package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAllNotes();

    Note findNoteById(Long noteId);

    Note saveNote(Note note);

    Note updateNote(Note note, Long noteId);

    void deleteNote(Long noteId);
}
