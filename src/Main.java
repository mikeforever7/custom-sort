import enums.SortOption;
import model.Student;
import service.SortService;
import strategy.GradeSortStrategy;
import strategy.GroupSortStrategy;
import strategy.QuickSort;
import strategy.RecordBookSortStrategy;
import strategy.SortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<SortOption, SortStrategy> strategies = Map.of(
                SortOption.GROUP, new GroupSortStrategy(),
                SortOption.AVERAGE_GRADE, new GradeSortStrategy(),
                SortOption.RECORD_BOOK, new RecordBookSortStrategy()
        );

        List<Student> students = new ArrayList<>();
        students.add(new Student(302, 4.2, 1003));
        students.add(new Student(101, 3.7, 1001));
        students.add(new Student(205, 4.9, 1005));
        students.add(new Student(101, 4.5, 1002));
        students.add(new Student(205, 3.9, 1004));

        System.out.println("До сортировки:");
        printStudents(students);

        QuickSort quickSort = new QuickSort();
        SortService sortService = new SortService(quickSort);

        // Вот тут мы выбираем по какому параметру будем сортировать
        List<Student> sortedStudents = sortService.sort(students, strategies.get(SortOption.GROUP));

        System.out.println("После сортировки:");
        printStudents(sortedStudents);
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

}
