package service;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.GradeSortStrategy;
import strategy.GroupSortStrategy;
import strategy.QuickSort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortServiceTest {

    private SortService sortService;
    private Student s1;
    private Student s2;

    @BeforeEach
    void setUp() {
        QuickSort sorter = new QuickSort();
        sortService = new SortService(sorter);

        s1 = new Student.Builder().groupNumber(20).averageGrade(4.0).recordBookNumber(200).build();
        s2 = new Student.Builder().groupNumber(10).averageGrade(9.0).recordBookNumber(100).build();
    }

    @Test
    void shouldDelegateSortingToQuickSort() {
        List<Student> students = List.of(s1, s2);

        List<Student> sortedByGrade = sortService.sort(students, new GradeSortStrategy());
        List<Student> sortedByGroup = sortService.sort(students, new GroupSortStrategy());

        assertEquals(4.0, sortedByGrade.get(0).getAverageGrade());
        assertEquals(9.0, sortedByGrade.get(1).getAverageGrade());

        assertEquals(10, sortedByGroup.get(0).getGroupNumber());
        assertEquals(20, sortedByGroup.get(1).getGroupNumber());
    }
}