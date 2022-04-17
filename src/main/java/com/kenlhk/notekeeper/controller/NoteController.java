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

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable("noteId") Long noteId) {
        return ResponseEntity.ok(noteMapper.findNoteById(noteId));
    }

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> saveNote(@Valid @RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteMapper.saveNote(request));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteResponse> updateNote(@RequestBody NoteRequest request, @PathVariable("id") Long noteId) {
        return ResponseEntity.ok(noteMapper.updateNote(request, noteId));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity deleteNote(@PathVariable("id") Long noteId) {
        noteMapper.deleteNote(noteId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{noteId}/tags")
    public ResponseEntity<TagResponse> addTag(@RequestBody TagRequest request, @PathVariable("noteId") Long noteId) {
        return ResponseEntity.ok(tagMapper.addTag(request, noteId));
    }

    @DeleteMapping("/{noteId}/tags")
    public ResponseEntity removeTag(@RequestBody TagRequest request, @PathVariable("noteId") Long noteId) {
        tagMapper.removeTag(request, noteId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{noteId}/sources")
    public ResponseEntity<SourceResponse> addSource(@RequestBody SourceRequest request, @PathVariable("noteId") Long noteId) {
        return ResponseEntity.ok(sourceMapper.addSource(request, noteId));
    }

    @DeleteMapping("/{noteId}/sources")
    public ResponseEntity removeSource(@PathVariable("noteId") Long noteId) {
        sourceMapper.removeSource(noteId);
        return ResponseEntity.noContent().build();
    }
}
