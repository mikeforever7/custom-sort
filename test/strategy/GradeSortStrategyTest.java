package strategy;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class GradeSortStrategyTest {

    private GradeSortStrategy strategy;
    private Student lowGradeStudent;
    private Student highGradeStudent;

    @BeforeEach
    void setUp() {
        strategy = new GradeSortStrategy();
        lowGradeStudent = new Student.Builder().groupNumber(1).averageGrade(4.0).recordBookNumber(100).build();
        highGradeStudent = new Student.Builder().groupNumber(1).averageGrade(9.5).recordBookNumber(100).build();
    }

    @Test
    void shouldCompareStudentsByAverageGradeCorrectly() {
        Comparator<Student> comparator = strategy.getComparator();

        assertTrue(comparator.compare(lowGradeStudent, highGradeStudent) < 0);
        assertTrue(comparator.compare(highGradeStudent, lowGradeStudent) > 0);
        assertEquals(0, comparator.compare(lowGradeStudent, lowGradeStudent));
    }
}