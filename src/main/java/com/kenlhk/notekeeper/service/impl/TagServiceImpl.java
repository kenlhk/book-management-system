package com.kenlhk.notekeeper.service.impl;

import com.kenlhk.notekeeper.exception.ApiRequestException;
import com.kenlhk.notekeeper.model.Note;
import com.kenlhk.notekeeper.model.Tag;
import com.kenlhk.notekeeper.repository.NoteRepository;
import com.kenlhk.notekeeper.repository.TagRepository;
import com.kenlhk.notekeeper.service.AuthenticationService;
import com.kenlhk.notekeeper.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    private final AuthenticationService authenticationService;

    @Override
    public Tag addTag(Tag tag, long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        if (!authenticationService.isCurrentUser(note.getUser())) {
            throw new ApiRequestException("Unauthorized access.", HttpStatus.UNAUTHORIZED);
        }
        Optional<Tag> tagFromDb = tagRepository.findByName(tag.getName());
        if (tagFromDb.isEmpty()) {
            tag = tagRepository.save(tag);
        } else {
            tag = tagFromDb.get();
        }
        note.getTags().add(tag);
        noteRepository.save(note);
        return tag;
    }

    @Override
    public void removeTag(Tag tag, long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ApiRequestException("Note not found.", HttpStatus.NOT_FOUND));
        if (!authenticationService.isCurrentUser(note.getUser())) {
            throw new ApiRequestException("Unauthorized access.", HttpStatus.UNAUTHORIZED);
        }
        tag = tagRepository.findByName(tag.getName())
                .orElseThrow(() -> new ApiRequestException("Tag not found.", HttpStatus.NOT_FOUND));
        Set<Tag> noteTags = note.getTags();
        if (noteTags.contains(tag)) {
            noteTags.remove(tag);
            noteRepository.save(note);
            try {
                tagRepository.delete(tag);
            } catch (Exception ignored) {
            }
        }
    }
}
