package service;

import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ManualStudentInputTest {

    private final InputStream originalIn = System.in;

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalIn);
    }

    @Test
    void shouldCreateStudentFromConsoleInput() {
        String simulatedInput = "10\n8.5\n12345\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ManualStudentInput inputService = new ManualStudentInput();
        Student student = inputService.fill();

        assertNotNull(student);
        assertEquals(10, student.getGroupNumber());
        assertEquals(8.5, student.getAverageGrade());
        assertEquals(12345, student.getRecordBookNumber());
    }
}