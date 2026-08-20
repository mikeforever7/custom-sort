package strategy;

import model.Student;

import java.util.Comparator;

public class GroupSortStrategy implements SortStrategy {

    @Override
    public Comparator<Student> getComparator() {
        return Comparator.comparingInt(Student::getGroupNumber);
    }
}
