package com.kenlhk.notekeeper.service.impl;

import com.kenlhk.notekeeper.domain.Note;
import com.kenlhk.notekeeper.domain.Tag;
import com.kenlhk.notekeeper.exception.ApiRequestException;
import com.kenlhk.notekeeper.repository.NoteRepository;
import com.kenlhk.notekeeper.repository.TagRepository;
import com.kenlhk.notekeeper.service.AuthenticationService;
import com.kenlhk.notekeeper.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<Note> findAllNotes() {
        return noteRepository.findAllByUserId(authenticationService.findCurrentUser().getId());
    }

    @Override
    public Note findNoteById(long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        if (!authenticationService.isCurrentUser(note.getUser())) {
            throw new ApiRequestException("Unauthorized access.", HttpStatus.UNAUTHORIZED);
        }
        return note;
    }

    @Override
    public Note saveNote(Note note) {
        note.setUser(authenticationService.findCurrentUser());
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Note note, long id) {
        Note noteFromDb = noteRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        if (!authenticationService.isCurrentUser(noteFromDb.getUser())) {
            throw new ApiRequestException("Unauthorized access.", HttpStatus.UNAUTHORIZED);
        }
        noteFromDb.setSubject(note.getSubject());
        noteFromDb.setContent(note.getContent());
        noteFromDb.setSource(note.getSource());
        noteFromDb.setTags(note.getTags());
        noteFromDb.setEditedAt(LocalDateTime.now());
        return noteRepository.save(noteFromDb);
    }

    @Override
    public void deleteNote(long id) {
        Note noteFromDb = noteRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        if (!authenticationService.isCurrentUser(noteFromDb.getUser())) {
            throw new ApiRequestException("Unauthorized access.", HttpStatus.UNAUTHORIZED);
        }
        noteRepository.delete(noteFromDb);
    }

    @Override
    public Note addTag(Tag tag, long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        Optional<Tag> tagFromDb = tagRepository.findByName(tag.getName());
        if (tagFromDb.isEmpty()) {
            note.getTags().add(tagRepository.save(tag));
        } else {
            note.getTags().add(tagFromDb.get());
        }
        return noteRepository.save(note);
    }

    @Override
    public void removeTag(Tag tag, long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        tag = tagRepository.findByName(tag.getName())
                .orElseThrow(() -> new ApiRequestException("Tag not found.", HttpStatus.NOT_FOUND));
        Set<Tag> tags = note.getTags();
        if (tags.contains(tag)) {
            tags.remove(tag);
            noteRepository.save(note);
            try {
                tagRepository.delete(tag);
            } catch (Exception ignored) {
            }
        }
    }
}
