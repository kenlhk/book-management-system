package com.kenlhk.notekeeper.service.impl;

import com.kenlhk.notekeeper.exception.ApiRequestException;
import com.kenlhk.notekeeper.model.Note;
import com.kenlhk.notekeeper.model.Source;
import com.kenlhk.notekeeper.repository.NoteRepository;
import com.kenlhk.notekeeper.repository.SourceRepository;
import com.kenlhk.notekeeper.service.AuthenticationService;
import com.kenlhk.notekeeper.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {
    private final NoteRepository noteRepository;
    private final SourceRepository sourceRepository;
    private final AuthenticationService authenticationService;

    @Override
    public Source addSource(Source source, long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        if (!authenticationService.isCurrentUser(note.getUser())) {
            throw new ApiRequestException("Unauthorized access.", HttpStatus.UNAUTHORIZED);
        }
        source = sourceRepository.save(source);
        if(note.getSource() == null){
            note.setSource(source);
            noteRepository.save(note);
        } else {
            throw new ApiRequestException("The note has a source already.", HttpStatus.BAD_REQUEST);
        }
        return source;
    }

    @Override
    public void removeSource(long id) {

    }
}
