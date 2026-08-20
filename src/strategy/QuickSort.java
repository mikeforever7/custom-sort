package strategy;

import model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSort {

    public List<Student> sort(List<Student> partOfList, Comparator<Student> comparator) {
        if (partOfList.size() <= 1) {
            return partOfList;
        }
        List<Student> part1 = new ArrayList<>();
        List<Student> equal = new ArrayList<>();
        List<Student> part2 = new ArrayList<>();
        int pivotIndex = partOfList.size() / 2;
        for (int i = 0; i < partOfList.size(); i++) {
            int comparison = comparator.compare(partOfList.get(i), partOfList.get(pivotIndex));
            if (comparison < 0) {
                part1.add(partOfList.get(i));
            } else if (comparison > 0) {
                part2.add(partOfList.get(i));
            } else {
                equal.add(partOfList.get(i));
            }
        }
        List<Student> result1 = sort(part1, comparator);
        List<Student> result2 = sort(part2, comparator);
        List<Student> result = new ArrayList<>(result1);
        result.addAll(equal);
        result.addAll(result2);
        return result;
    }
}
