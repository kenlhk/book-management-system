package com.kenlhk.notekeeper.controller;

import com.kenlhk.notekeeper.dto.note.NoteRequest;
import com.kenlhk.notekeeper.dto.note.NoteResponse;
import com.kenlhk.notekeeper.dto.source.SourceRequest;
import com.kenlhk.notekeeper.dto.source.SourceResponse;
import com.kenlhk.notekeeper.dto.tag.TagRequest;
import com.kenlhk.notekeeper.dto.tag.TagResponse;
import com.kenlhk.notekeeper.mapper.NoteMapper;
import com.kenlhk.notekeeper.mapper.SourceMapper;
import com.kenlhk.notekeeper.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {
    private final NoteMapper noteMapper;
    private final TagMapper tagMapper;
    private final SourceMapper sourceMapper;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> findAllNotes() {
        return ResponseEntity.ok(noteMapper.findAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable("id") long id) {
        return ResponseEntity.ok(noteMapper.findNoteById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> saveNote(@Valid @RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteMapper.saveNote(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@RequestBody NoteRequest request, @PathVariable("id") long id) {
        return ResponseEntity.ok(noteMapper.updateNote(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable("id") long id) {
        noteMapper.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/tags")
    public ResponseEntity<TagResponse> addTag(@RequestBody TagRequest request, @PathVariable("id") long noteId) {
        return ResponseEntity.ok(tagMapper.addTag(request, noteId));
    }

    @DeleteMapping("/{id}/tags")
    public ResponseEntity removeTag(@RequestBody TagRequest request, @PathVariable("id") long noteId) {
        tagMapper.removeTag(request, noteId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/sources")
    public ResponseEntity<SourceResponse> addSource(@RequestBody SourceRequest request, @PathVariable("id") long noteId) {
        return ResponseEntity.ok(sourceMapper.addSource(request, noteId));
    }

    @DeleteMapping("/{id}/sources")
    public ResponseEntity removeSource(@PathVariable long noteId) {
        return ResponseEntity.ok().build();
    }
}
