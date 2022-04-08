package com.kenlhk.notekeeper;

import com.kenlhk.notekeeper.domain.Note;
import com.kenlhk.notekeeper.domain.source.Book;
import com.kenlhk.notekeeper.repository.NoteRepository;
import com.kenlhk.notekeeper.repository.SourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoteKeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteKeeperApplication.class, args);
    }

}
