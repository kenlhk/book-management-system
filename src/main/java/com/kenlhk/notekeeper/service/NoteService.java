package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.domain.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAllNotes();
}
