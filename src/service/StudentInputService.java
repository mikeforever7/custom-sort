package service;

import collection.AwesomeArrayList;
import model.Student;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StudentInputService {

    public AwesomeArrayList inputStudents() {
        AwesomeArrayList studentList = new AwesomeArrayList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите способ заполнения студентов");
            System.out.println("1- вручную");
            System.out.println("2- случайно");
            System.out.println("3- из файла");
            System.out.println("0- выйти");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Ввод завершен");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите количество студентов, которых нужно заполнить вручную");
                    int manualCount = scanner.nextInt();
                    FillStrategy manualStrategy = new ManualStudentInput();

                    AwesomeArrayList manualStream = Stream.iterate(0, i -> i + 1)
                            .limit(manualCount)
                            .map(i -> manualStrategy.fill())
                            .peek(student -> studentList.add(studentList.size(), student))
                            .collect(Collectors.toCollection(AwesomeArrayList::new));

                    manualStream.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Введите количество студентов, которых нужно заполнить случайно");
                    int randomCount = scanner.nextInt();
                    FillStrategy randomStrategy = new RandomStudentGenerator();

                    AwesomeArrayList randomStream = Stream.generate(() -> randomStrategy.fill())
                            .limit(randomCount)
                            .peek(student -> studentList.add(studentList.size(), student))
                            .collect(Collectors.toCollection(AwesomeArrayList::new));

//                    .map(student -> {
//                    studentList.add(studentList.size(), student);
//                    return student;
//                })
//                        .collect(Collectors.toCollection(AwesomeArrayList::new));










                    randomStream.forEach(System.out::println);
                    break;
                case 3:
                    //TODO здесь должен быть вызов чтения из файла
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