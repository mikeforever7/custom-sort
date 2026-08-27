package ui;

import enums.SortOption;
import enums.SortType;
import model.Student;
import service.FillStrategy;
import service.ManualStudentInput;
import service.SortService;
import service.StudentCountService;
import service.StudentFileService;
import service.StudentInputService;
import strategy.GradeSortStrategy;
import strategy.GroupSortStrategy;
import strategy.RecordBookSortStrategy;
import strategy.SortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ConsoleMenu {
    private final Scanner scanner;
    private final SortService sortService;
    private final StudentInputService studentInputService;
    private final StudentFileService studentFileService;
    private final StudentCountService studentCountService;
    private List<Student> students = new ArrayList<>();

    public ConsoleMenu(Scanner scanner, SortService sortService, StudentInputService studentInputService, StudentFileService studentFileService, StudentCountService studentCountService) {
        this.scanner = scanner;
        this.sortService = sortService;
        this.studentInputService = studentInputService;
        this.studentFileService = studentFileService;
        this.studentCountService = studentCountService;
    }

    Map<SortOption, SortStrategy> strategies = Map.of(
            SortOption.GROUP, new GroupSortStrategy(),
            SortOption.AVERAGE_GRADE, new GradeSortStrategy(),
            SortOption.RECORD_BOOK, new RecordBookSortStrategy()
    );

    private int readInt(int min, int max) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Введите число от " + min + " до " + max);
            } catch (NumberFormatException e) {
                System.out.println("Необходимо ввести целое число");
            }
        }
    }


    public void run() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Вас приветствует приложение для сортировки студентов!");
        System.out.println("-----------------------------------------------------");

        boolean running = true;
        while (running) {
            printMainMenu();

            switch (readInt(0, 5)) {
                case 1 -> inputStudents();
                case 2 -> sortStudents();
                case 3 -> printStudents(students);
                case 4 -> saveStudents();
                case 5 -> countStudentOccurrences();
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private void inputStudents() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите способ добавления студентов");
            System.out.println("1- вручную");
            System.out.println("2- случайно");
            System.out.println("3- из файла");
            System.out.println("0- выйти");

            switch (readInt(0, 3)) {
                case 1:
                    int manualCount;
                    while (true) {
                        System.out.println("Введите количество студентов, которых нужно добавить вручную");
                        if (scanner.hasNextInt()) {
                            manualCount = scanner.nextInt();
                            if (manualCount <= 0) {
                                System.out.println("Количество должно быть больше 0. Введите еще раз.");
                                continue;
                            }
                            break;
                        } else {
                            String badInput = scanner.next();
                            System.out.println("Ошибка!" + badInput + "это не целое число. Введите еще раз.");
                        }
                    }
                    students.addAll(studentInputService.inputManualStudents(manualCount));
                    System.out.println("Студенты успешно добавлены");
                    break;
                case 2:
                    int randomCount;
                    while (true) {
                        System.out.println("Введите количество студентов, которых нужно добавить случайно");
                        if (scanner.hasNextInt()) {
                            randomCount = scanner.nextInt();
                            if (randomCount <= 0) {
                                System.out.println("Количество должно быть больше 0. Введите еще раз.");
                                continue;
                            }
                            break;
                        } else {
                            String badInput = scanner.next();
                            System.out.println("Ошибка!" + badInput + "это не целое число. Введите еще раз.");
                        }
                    }
                    students.addAll(studentInputService.inputRandomStudents(randomCount));
                    System.out.println("Студенты успешно добавлены");
                    break;
                case 3:
                    System.out.println("Введите путь к файлу:");
                    String filePath = scanner.nextLine();
                    students.addAll(studentInputService.inputFromFileStudents(filePath));
                    System.out.println("Студенты успешно добавлены");
                    break;
                case 0:
                    System.out.println("Ввод завершен");
                    running = false;
                    break;
            }
        }
    }

    private void sortStudents() {
        while (true) {
            if (students.isEmpty()) {
                System.out.println("Список студентов пуст");
                return;
            }
            SortType sortType = chooseSortType();
            if (sortType == null) {
                return;
            }
            SortOption sortOption = chooseSortField(sortType);
            if (sortOption == null) {
                continue;
            }
            if (sortType == SortType.QUICK_SORT) {
                students = sortService.sort(students, strategies.get(sortOption));
                System.out.println("Студенты успешно отсортированы");
                return;
            } else if (sortType == SortType.SELECTIVE) {
                students = sortService.selectiveSort(students, sortOption.getIntExtractor());
                System.out.println("Студенты успешно отсортированы");
                return;
            }
        }
    }

    private SortType chooseSortType() {
        System.out.println("\nВыберите сортировку:");
        System.out.println("1. Стандартная сортировка");
        System.out.println("2. Выборочная сортировка по четным числам");
        System.out.println("0. Назад");

        return switch (readInt(0, 3)) {
            case 1 -> SortType.QUICK_SORT;
            case 2 -> SortType.SELECTIVE;
            case 0 -> null;
            default -> null;
        };
    }

    private SortOption chooseSortField(SortType sortType) {
        System.out.println("\nВыберите поле:");
        System.out.println("1. Номер группы");
        System.out.println("2. Средний балл");
        System.out.println("3. Номер зачётки");
        System.out.println("0. Назад");

        int choice = readInt(0, 3);
        if (choice == 0) {
            return null;
        }
        SortOption sortOption = switch (choice) {
            case 1 -> SortOption.GROUP;
            case 2 -> SortOption.AVERAGE_GRADE;
            case 3 -> SortOption.RECORD_BOOK;
            default -> null;
        };
        if (sortType == SortType.SELECTIVE && sortOption == SortOption.AVERAGE_GRADE) {
            System.out.println("Выборочная сортировка работает только с целыми числами!");
        }
        return sortOption;
    }

    private void saveStudents() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст. Сохранять нечего.");
            return;
        }
        System.out.println("Введите путь к файлу для сохранения:");

        String filePath = scanner.nextLine().trim();
        studentFileService.appendStudents(filePath, students);
    }

    private void countStudentOccurrences() {
        System.out.println("Введите данные студента которого ищем");
        FillStrategy fillStrategy = new ManualStudentInput();
        int count = studentCountService.countOccurrences(students, fillStrategy.fill());
        if (count == 0) {
            System.out.println("Студент не найден");
        } else {
            System.out.println("Количество копий студента в списке: " + count);
        }
    }

    private void printStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст!");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void printMainMenu() {
        System.out.println("\n====================== МЕНЮ =========================");
        System.out.println("1. Добавить студентов");
        System.out.println("2. Отсортировать студентов");
        System.out.println("3. Вывести студентов");
        System.out.println("4. Сохранить результат в файл");
        System.out.println("5. Проверить количество копий студента в списке");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }
}
