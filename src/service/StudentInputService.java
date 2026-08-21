package service;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentInputService {

    public List<Student> inputStudents() {
        List<Student> studentList = new ArrayList<>();
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
                    for (int i = 0; i < manualCount; i++) {
                        System.out.println("\n--- Студент " + (i + 1) + " (вручную) ---");
                        Student student = manualStrategy.fill();
                        studentList.add(student);
                        System.out.println(student);
                    }
                    break;
                case 2:
                    System.out.println("Введите количество студентов, которых нужно заполнить случайно");
                    int randomCount = scanner.nextInt();
                    FillStrategy randomStrategy = new RandomStudentGenerator();
                    for (int i = 0; i < randomCount; i++) {
                        System.out.println("\n--- Студент " + (i + 1) + " (случайно) ---");
                        Student student = randomStrategy.fill();
                        studentList.add(student);
                        System.out.println(student);
                    }
                    break;
                case 3:
                    //TODO здесь должен быть вызов чтения из файла
                    System.out.println("Чтение из файла будет реализовано позже");
                    break;
                default:
                    System.out.println("Неверный ввод!");

            }

        }
        System.out.println("Всего студентов в списке: " +studentList.size());
        return studentList;

    }
}
