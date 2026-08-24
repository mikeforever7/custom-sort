package service;

import model.Student;
import strategy.QuickSort;
import strategy.SelectiveSort;
import strategy.SortStrategy;

import java.util.List;
import java.util.function.ToIntFunction;

public class SortService {
    private final QuickSort sorter;
    private final SelectiveSort selectiveSorter;

    public SortService(QuickSort sorter, SelectiveSort selectiveSorter) {
        this.sorter = sorter;
        this.selectiveSorter = selectiveSorter;
    }

    public List<Student> sort(List<Student> students, SortStrategy strategy) {
        return sorter.sort(students, strategy.getComparator());
    }

    public List<Student> selectiveSort(List<Student> students, ToIntFunction<Student> fieldExtractor) {
        return selectiveSorter.sort(students, fieldExtractor);
    }
}
