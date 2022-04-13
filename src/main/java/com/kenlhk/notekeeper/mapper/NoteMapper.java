package com.kenlhk.notekeeper.mapper;

import com.kenlhk.notekeeper.domain.Note;
import com.kenlhk.notekeeper.dto.note.NoteRequest;
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

    public NoteResponse findNoteById(Long id){
        return commonMapper.map(noteService.findNoteById(id), NoteResponse.class);
    }

    public NoteResponse saveNote(NoteRequest request){
        Note note = commonMapper.map(request, Note.class);
        return commonMapper.map(noteService.saveNote(note), NoteResponse.class);
    }

    public NoteResponse updateNote(NoteRequest request, long id){
        Note note = commonMapper.map(request, Note.class);
        return commonMapper.map(noteService.updateNote(note, id), NoteResponse.class);
    }

    public void deleteNote(long id){
        noteService.deleteNote(id);
    }
}
