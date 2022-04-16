package com.kenlhk.notekeeper.repository;

import com.kenlhk.notekeeper.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword1 = passwordEncoder.encode("test12345");
        String encodedPassword2 = passwordEncoder.encode("test67890");

        User user1 = new User("tester1", encodedPassword1, "test1@test.com");
        User savedUser1 = userRepository.save(user1);

        User user2 = new User("tester2", encodedPassword2, "test2@test.com");
        User savedUser2 = userRepository.save(user2);

        assertNotNull(savedUser1);
        assertTrue(savedUser1.getId() > 0);

        assertNotNull(savedUser2);
        assertTrue(savedUser2.getId() > 0);
    }
}
