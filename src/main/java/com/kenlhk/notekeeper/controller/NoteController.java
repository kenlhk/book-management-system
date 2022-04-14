package com.kenlhk.notekeeper.controller;

import com.kenlhk.notekeeper.dto.mapper.NoteMapper;
import com.kenlhk.notekeeper.dto.note.NoteRequest;
import com.kenlhk.notekeeper.dto.note.NoteResponse;
import com.kenlhk.notekeeper.dto.tag.TagRequest;
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

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes(){
        return ResponseEntity.ok(noteMapper.findAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable("id") long id){
        return ResponseEntity.ok(noteMapper.findNoteById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> saveNote(@Valid @RequestBody NoteRequest request){
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
    public ResponseEntity<NoteResponse> addTag(@RequestBody TagRequest request, @PathVariable("id") long id) {
        return ResponseEntity.ok(noteMapper.addTag(request, id));
    }

    @DeleteMapping("/{id}/tags")
    public ResponseEntity removeTag(@RequestBody TagRequest request, @PathVariable("id") long id) {
        noteMapper.removeTag(request, id);
        return ResponseEntity.noContent().build();
    }
}
