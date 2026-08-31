package strategy;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class GradeSortStrategyTest {
    private GradeSortStrategy strategy;
    private Student lowGradeStudent_1, lowGradeStudent_2;
    private Student highGradeStudent;

    @BeforeEach
    void setUp() {
        strategy = new GradeSortStrategy();
        lowGradeStudent_1 = new Student.Builder().groupNumber(1).averageGrade(4.0).recordBookNumber(100).build();
        lowGradeStudent_2 = new Student.Builder().groupNumber(2).averageGrade(4.0).recordBookNumber(200).build();
        highGradeStudent = new Student.Builder().groupNumber(1).averageGrade(9.5).recordBookNumber(100).build();
    }

    @Test
    void shouldCompareStudentsByAverageGradeCorrectly() {
        Comparator<Student> comparator = strategy.getComparator();

        assertTrue(comparator.compare(lowGradeStudent_1, highGradeStudent) < 0);
        assertTrue(comparator.compare(highGradeStudent, lowGradeStudent_1) > 0);
        assertEquals(0, comparator.compare(lowGradeStudent_1, lowGradeStudent_2));
    }
}