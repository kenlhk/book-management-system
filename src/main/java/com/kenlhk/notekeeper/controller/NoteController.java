package com.kenlhk.notekeeper.controller;

import com.kenlhk.notekeeper.dto.note.NoteResponse;
import com.kenlhk.notekeeper.mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteMapper noteMapper;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes(){
        return ResponseEntity.ok(noteMapper.findAllNotes());
    }

}
