package service;

import model.Student;

import java.util.Scanner;

public class ManualStudentInput implements FillStrategy {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public Student fill() {
        int group;
        double grade;
        int recordBook;

        System.out.println("Введите номер группы");
        group = scanner.nextInt();
        System.out.println("Введите средний балл");
        grade = scanner.nextDouble();
        System.out.println("Введите номер зачетки");
        recordBook = scanner.nextInt();
        return new Student(group, grade, recordBook);
    }
}