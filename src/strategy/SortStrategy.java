package strategy;

import model.Student;

import java.util.Comparator;

public interface SortStrategy {
    Comparator<Student> getComparator();
}
