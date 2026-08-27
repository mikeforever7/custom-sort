package service;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomStudentGeneratorTest {

    private RandomStudentGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new RandomStudentGenerator();
    }

    @Test
    void shouldGenerateNotNullStudent() {
        Student student = generator.fill();

        assertNotNull(student);
    }

    @Test
    void shouldGenerateStudentWithValidFields() {
        for (int i = 0; i < 100; i++) {
            Student student = generator.fill();

            assertTrue(student.getGroupNumber() >= 1 && student.getGroupNumber() <= 100,
                    "Номер группы вышел за пределы допустимого диапазона");
            assertTrue(student.getAverageGrade() >= 0.0 && student.getAverageGrade() <= 10.0,
                    "Средний балл вышел за пределы допустимого диапазона");
            assertTrue(student.getRecordBookNumber() >= 1 && student.getRecordBookNumber() <= 1000000,
                    "Номер зачетной книжки вышел за пределы допустимого диапазона");
        }
    }
}