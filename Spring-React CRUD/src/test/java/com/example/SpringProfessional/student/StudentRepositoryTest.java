package com.example.SpringProfessional.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        //Given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila",
                email,
                Gender.FEMALE,
                20

        );
        underTest.save(student);
        //When
        Boolean expected = underTest.selectExistsEmail(email);
        //Then
        assertThat(expected).isTrue();
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExists() {
        //Given
        String email = "jamila@gmail.com";
        //When
        Boolean expected = underTest.selectExistsEmail(email);
        //Then
        assertThat(expected).isFalse();
    }
}