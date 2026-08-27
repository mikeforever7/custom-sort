package service;

import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentInputServiceTest {

    private final InputStream originalIn = System.in;

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalIn);
    }

    @Test
    void shouldReturnPopulatedListOnRandomInputAndExit() {
        // 2 2 0 епт
        String simulatedInput = "2\n2\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        StudentInputService service = new StudentInputService();
        List<Student> students = service.inputStudents();

        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void shouldReturnEmptyListWhenExitedImmediately() {
        String simulatedInput = "0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        StudentInputService service = new StudentInputService();
        List<Student> students = service.inputStudents();

        assertNotNull(students);
        assertTrue(students.isEmpty());
    }
}