package strategy;

import model.Student;

import java.util.Comparator;

public class GradeSortStrategy implements SortStrategy {

    @Override
    public Comparator<Student> getComparator() {
        return Comparator.comparingDouble(Student::getAverageGrade);
    }
}

