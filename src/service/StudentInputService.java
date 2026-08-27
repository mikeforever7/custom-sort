package service;

import collection.AwesomeArrayList;
import model.Student;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentInputService {

    public List<Student> inputStudents() {
        List<Student> studentList = new AwesomeArrayList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите способ заполнения студентов");
            System.out.println("1- вручную");
            System.out.println("2- случайно");
            System.out.println("3- из файла");
            System.out.println("0- выйти");

            if (!scanner.hasNextInt()) {
                String badInput = scanner.next();
                System.out.println("Неверный ввод! \"" + badInput + "\" это не целое число. Введите число от 0 до 3");
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Ввод завершен");
                break;
            }

            switch (choice) {
                case 1:
                    int manualCount;
                    while (true) {
                        System.out.println("Введите количество студентов, которых нужно заполнить вручную");
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

                    FillStrategy manualStrategy = new ManualStudentInput();
                    List<Student> manualStream = Stream.iterate(0, i -> i + 1).limit(manualCount).map(i -> manualStrategy.fill()).peek(student -> studentList.add(studentList.size(), student)).collect(Collectors.toCollection(AwesomeArrayList::new));

                    manualStream.forEach(System.out::println);
                    break;

                case 2:
                    int randomCount;
                    while (true) {
                        System.out.println("Введите количество студентов, которых нужно заполнить случайно");
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

                    FillStrategy randomStrategy = new RandomStudentGenerator();
                    List<Student> randomStream = Stream.generate(() -> randomStrategy.fill()).limit(randomCount).peek(student -> studentList.add(studentList.size(), student)).collect(Collectors.toCollection(AwesomeArrayList::new));

                    randomStream.forEach(System.out::println);
                    break;

                case 3:
                    // TODO здесь должен быть вызов чтения из файла
                    System.out.println("Чтение из файла будет реализовано позже");
                    break;

                default:
                    System.out.println("Неверный ввод!");
            }
        }

        System.out.println("Всего студентов в списке: " + studentList.size());
        return studentList;
    }
}