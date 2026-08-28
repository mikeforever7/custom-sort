package service;

import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ManualStudentInputTest {

    private final InputStream originalIn = System.in;
    private final Locale originalLocale = Locale.getDefault();

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalIn);
        Locale.setDefault(originalLocale);
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