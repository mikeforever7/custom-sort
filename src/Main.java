import enums.SortOption;
import model.Student;
import service.*;
import strategy.GradeSortStrategy;
import strategy.GroupSortStrategy;
import strategy.QuickSort;
import strategy.RecordBookSortStrategy;
import strategy.SelectiveSort;
import strategy.SortStrategy;
import collection.AwesomeArrayList;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<SortOption, SortStrategy> strategies = Map.of(
                SortOption.GROUP, new GroupSortStrategy(),
                SortOption.AVERAGE_GRADE, new GradeSortStrategy(),
                SortOption.RECORD_BOOK, new RecordBookSortStrategy()
        );
        //========================================тест add============================================================
//        System.out.println("Тест вставки по индексу:");
//        AwesomeArrayList list = new AwesomeArrayList();
//        list.add(0, new Student(101, 3.7, 1001));
//        list.add(0, new Student(202, 4.2, 1002));
//        list.add(0, new Student(303, 4.5, 1003));
//        System.out.println("До вставки:");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        list.add(1, new Student(404, 3.9, 4000));
//        System.out.println("После вставки:");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        //==============================================================================================================
//        List<Student> students = new ArrayList<>();
//        students.add(new Student(302, 4.2, 1003));
//        students.add(new Student(101, 3.7, 1001));
//        students.add(new Student(205, 4.9, 1005));
//        students.add(new Student(101, 4.5, 1002));
//        students.add(new Student(205, 3.9, 1004));

        StudentInputService studentInputService = new StudentInputService();
        AwesomeArrayList students = studentInputService.inputStudents();
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }

        System.out.println("До сортировки:");
        printStudents(students);

        QuickSort quickSort = new QuickSort();
        SelectiveSort selectiveSort = new SelectiveSort();
        SortService sortService = new SortService(quickSort, selectiveSort);

        // Вот тут мы выбираем по какому параметру будем сортировать
//        List<Student> sortedStudents = sortService.sort(students, strategies.get(SortOption.GROUP));
        List<Student> sortedStudents = sortService.selectiveSort(students, Student::getRecordBookNumber);

        System.out.println("После сортировки:");
        printStudents(sortedStudents);
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }





    }
}