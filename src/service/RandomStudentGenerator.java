package service;

import model.Student;

import java.util.Random;

public class RandomStudentGenerator implements FillStrategy {
    @Override
    public Student fill() {
        int group;
        double grade;
        int recordBook;

        group = random.nextInt(MIN_GROUP, MAX_GROUP + 1);
        grade = (MIN_GRADE + random.nextDouble() * MAX_GRADE - MIN_GRADE);    //до java 19 нет версии с аргументами для double
        recordBook = random.nextInt(MIN_RECORD_BOOK, MAX_RECORD_BOOK + 1);
        return new Student(group, grade, recordBook);
    }

    //TODO Эти диапазоны должны совпадать с будущей валидацией Builder
    private static final int MIN_GROUP = 1;
    private static final int MAX_GROUP = 100;
    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 10;
    private static final int MIN_RECORD_BOOK = 1;
    private static final int MAX_RECORD_BOOK = 1000000;

    private final Random random = new Random();

}
