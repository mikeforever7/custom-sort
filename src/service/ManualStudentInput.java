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

        while (true) {
            System.out.println("Введите номер группы (1-100)");
            if (scanner.hasNextInt()) {
                group = scanner.nextInt();
                if (group < 1 || group > 100) {
                    System.out.println("Ошибка! " + group + " это не допустимый номер группы. Введите еще раз.");
                    continue;
                }
                break;
            } else {
                String badInput = scanner.next();
                System.out.println("Ошибка! " + badInput + " это не допустимый номер группы. Введите еще раз.");
            }
        }
        while (true) {
            System.out.println("Введите средний балл (0-10)");
            if (scanner.hasNextDouble()) {
                grade = scanner.nextDouble();
                if (grade < 0 || grade > 10) {
                    System.out.println("Ошибка! " + grade + " это не допустимый средний балл. Введите еще раз.");
                    continue;
                }
                break;
            } else {
                String badInput = scanner.next();
                System.out.println("Ошибка! " + badInput + " это не допустимый средний балл. Введите еще раз.");
            }
        }
        while (true) {
            System.out.println("Введите номер зачетки (1-1000000)");
            if (scanner.hasNextInt()) {
                recordBook = scanner.nextInt();
                if (recordBook < 1 || recordBook > 1000000) {
                    System.out.println("Ошибка! " + recordBook + " это не допустимый номер зачетки. Введите еще раз.");
                    continue;
                }
                break;
            } else {
                String badInput = scanner.next();
                System.out.println("Ошибка! " + badInput + " это не допустимый номер зачетки. Введите еще раз.");
            }
        }
        return new Student.Builder().groupNumber(group).averageGrade(grade).recordBookNumber(recordBook).build();
    }
}