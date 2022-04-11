package com.kenlhk.notekeeper.mapper;

import com.kenlhk.notekeeper.dto.note.NoteResponse;
import com.kenlhk.notekeeper.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoteMapper {

    private final CommonMapper commonMapper;
    private final NoteService noteService;

    public List<NoteResponse> findAllNotes(){
        return commonMapper.map(noteService.findAllNotes(), NoteResponse.class);
    }
}
