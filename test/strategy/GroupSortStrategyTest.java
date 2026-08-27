package strategy;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class GroupSortStrategyTest {

    private GroupSortStrategy strategy;
    private Student group10Student;
    private Student group50Student;

    @BeforeEach
    void setUp() {
        strategy = new GroupSortStrategy();
        group10Student = new Student.Builder().groupNumber(10).averageGrade(5.0).recordBookNumber(100).build();
        group50Student = new Student.Builder().groupNumber(50).averageGrade(5.0).recordBookNumber(100).build();
    }

    @Test
    void shouldCompareStudentsByGroupNumberCorrectly() {
        Comparator<Student> comparator = strategy.getComparator();

        assertTrue(comparator.compare(group10Student, group50Student) < 0);
        assertTrue(comparator.compare(group50Student, group10Student) > 0);
        assertEquals(0, comparator.compare(group10Student, group10Student));
    }
}