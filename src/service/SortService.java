package service;

import model.Student;
import strategy.QuickSort;
import strategy.SortStrategy;

import java.util.List;

public class SortService {

    private final QuickSort sorter;

    public SortService(QuickSort sorter) {
        this.sorter = sorter;
    }

    public List<Student> sort(List<Student> students, SortStrategy strategy) {
        return sorter.sort(students, strategy.getComparator());
    }
}
