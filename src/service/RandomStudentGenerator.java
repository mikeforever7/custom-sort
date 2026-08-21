package service;

import model.Student;

import java.util.Random;


public class RandomStudentGenerator implements FillStrategy {
    //TODO Эти диапазоны должны совпадать с будущей валидацией Builder
    private static final int MIN_GROUP = 1;
    private static final int MAX_GROUP = 100;
    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 10;
    private static final int MIN_RECORD_BOOK = 1;
    private static final int MAX_RECORD_BOOK = 1000000;

    private final Random random = new Random();

    @Override
    public Student fill() {
        int group;
        double grade;
        int recordBook;

        group = random.nextInt(MIN_GROUP, MAX_GROUP + 1);
        grade = random.nextDouble(MIN_GRADE, MAX_GRADE);
        recordBook = random.nextInt(MIN_RECORD_BOOK, MAX_RECORD_BOOK + 1);
        return new Student(group, grade, recordBook);
    }
}