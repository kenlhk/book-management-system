package com.kenlhk.notekeeper.service.impl;

import com.kenlhk.notekeeper.domain.Note;
import com.kenlhk.notekeeper.repository.NoteRepository;
import com.kenlhk.notekeeper.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }
}
