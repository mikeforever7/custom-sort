package service;

import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentInputServiceTest {

    private final InputStream originalIn = System.in;

    private final StudentInputService service = new StudentInputService(null);

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalIn);
    }


    @Test
    void shouldGenerateRandomStudents(){
        List<Student> students = service.inputRandomStudents(2);

        assertNotNull(students);
        assertEquals(2, students.size());


    }

//
//    @Test
//    void shouldReadStudentsFromFile() {
//        List<Student> students = service.inputFromFileStudents("valid.csv");
//
//        assertNotNull(students);
//        assertEquals(2, students.size());
//    }

}