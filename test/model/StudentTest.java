package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void shouldCreateStudentWhenDataIsValid() {
        Student student = new Student.Builder()
                .groupNumber(10)
                .averageGrade(8.5)
                .recordBookNumber(12345)
                .build();

        assertNotNull(student);
        assertEquals(10, student.getGroupNumber());
        assertEquals(8.5, student.getAverageGrade());
        assertEquals(12345, student.getRecordBookNumber());
    }

    @Test
    void shouldThrowExceptionWhenGroupNumberIsInvalid() {
        Student.Builder builder = new Student.Builder();

        assertThrows(IllegalArgumentException.class, () -> builder.groupNumber(0));
        assertThrows(IllegalArgumentException.class, () -> builder.groupNumber(101));
    }

    @Test
    void shouldThrowExceptionWhenAverageGradeIsInvalid() {
        Student.Builder builder = new Student.Builder();

        assertThrows(IllegalArgumentException.class, () -> builder.averageGrade(-0.1));
        assertThrows(IllegalArgumentException.class, () -> builder.averageGrade(10.1));
    }

    @Test
    void shouldThrowExceptionWhenRecordBookNumberIsInvalid() {
        Student.Builder builder = new Student.Builder();

        assertThrows(IllegalArgumentException.class, () -> builder.recordBookNumber(0));
        assertThrows(IllegalArgumentException.class, () -> builder.recordBookNumber(1_000_001));
    }
}