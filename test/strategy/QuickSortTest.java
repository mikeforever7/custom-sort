package strategy;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    private QuickSort sorter;
    private Student s1;
    private Student s2;
    private Student s3;

    @BeforeEach
    void setUp() {
        sorter = new QuickSort();
        s1 = new Student.Builder().groupNumber(30).averageGrade(4.2).recordBookNumber(300).build();
        s2 = new Student.Builder().groupNumber(10).averageGrade(9.1).recordBookNumber(100).build();
        s3 = new Student.Builder().groupNumber(20).averageGrade(6.5).recordBookNumber(200).build();
    }

    @Test
    void shouldSortByAverageGrade() {
        List<Student> list = List.of(s1, s2, s3);
        SortStrategy strategy = new GradeSortStrategy();

        List<Student> result = sorter.sort(list, strategy.getComparator());

        assertEquals(4.2, result.get(0).getAverageGrade());
        assertEquals(6.5, result.get(1).getAverageGrade());
        assertEquals(9.1, result.get(2).getAverageGrade());
    }

    @Test
    void shouldSortByGroupNumber() {
        List<Student> list = List.of(s1, s2, s3);
        SortStrategy strategy = new GroupSortStrategy();

        List<Student> result = sorter.sort(list, strategy.getComparator());

        assertEquals(10, result.get(0).getGroupNumber());
        assertEquals(20, result.get(1).getGroupNumber());
        assertEquals(30, result.get(2).getGroupNumber());
    }

    @Test
    void shouldSortByRecordBookNumber() {
        List<Student> list = List.of(s1, s2, s3);
        SortStrategy strategy = new RecordBookSortStrategy();

        List<Student> result = sorter.sort(list, strategy.getComparator());

        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(200, result.get(1).getRecordBookNumber());
        assertEquals(300, result.get(2).getRecordBookNumber());
    }

    @Test
    void shouldHandleEmptyAndSingleElementLists() {
        List<Student> emptyList = new ArrayList<>();
        List<Student> singleList = List.of(s1);
        SortStrategy strategy = new GradeSortStrategy();

        List<Student> emptyResult = sorter.sort(emptyList, strategy.getComparator());
        List<Student> singleResult = sorter.sort(singleList, strategy.getComparator());

        assertTrue(emptyResult.isEmpty());
        assertEquals(1, singleResult.size());
        assertEquals(s1, singleResult.get(0));
    }
}