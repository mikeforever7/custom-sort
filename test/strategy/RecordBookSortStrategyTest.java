package strategy;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class RecordBookSortStrategyTest {

    private RecordBookSortStrategy strategy;
    private Student smallRecordBookStudent;
    private Student largeRecordBookStudent;

    @BeforeEach
    void setUp() {
        strategy = new RecordBookSortStrategy();
        smallRecordBookStudent = new Student.Builder().groupNumber(1).averageGrade(5.0).recordBookNumber(101).build();
        largeRecordBookStudent = new Student.Builder().groupNumber(1).averageGrade(5.0).recordBookNumber(999).build();
    }

    @Test
    void shouldCompareStudentsByRecordBookNumberCorrectly() {
        Comparator<Student> comparator = strategy.getComparator();

        assertTrue(comparator.compare(smallRecordBookStudent, largeRecordBookStudent) < 0);
        assertTrue(comparator.compare(largeRecordBookStudent, smallRecordBookStudent) > 0);
        assertEquals(0, comparator.compare(smallRecordBookStudent, smallRecordBookStudent));
    }
}