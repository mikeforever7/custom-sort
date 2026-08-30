package service;

import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentInputServiceTest {

    private final InputStream originalIn = System.in;

    private StudentInputService service;
    private StudentFileService fileService;

    @BeforeEach
    void setUp(){
        fileService = new StudentFileService();
        service = new StudentInputService(fileService);
    }

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

    @Test
    void shouldReadStudentsFromFile() {

        Student student1 = new Student.Builder()
                .groupNumber(10)
                .averageGrade(5.5)
                .recordBookNumber(1001)
                .build();

        Student student2 = new Student.Builder()
                .groupNumber(15)
                .averageGrade(8.0)
                .recordBookNumber(1002)
                .build();


        StudentFileService fakeFileService = new StudentFileService(){
            @Override
            public List<Student> readStudents(String filePath){
                return List.of(student1, student2);
            }
        };


        StudentInputService serviceWithFake = new StudentInputService(fakeFileService);

        //и все потому Student приватный
        List<Student> students = serviceWithFake.inputFromFileStudents("valid.csv");

        assertNotNull(students);
        assertEquals(2, students.size());
    }
}