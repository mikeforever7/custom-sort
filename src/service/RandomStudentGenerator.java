package service;

import model.Student;

import java.util.Random;

import static constans.Constants.*;


public class RandomStudentGenerator implements FillStrategy {
    //TODO Эти диапазоны должны совпадать с будущей валидацией Builder


    private final Random random = new Random();

    @Override
    public Student fill() {
        int group;
        double grade;
        int recordBook;

        group = random.nextInt(MIN_GROUP, MAX_GROUP + 1);
        grade = random.nextDouble(MIN_GRADE, MAX_GRADE);
        recordBook = random.nextInt(MIN_RECORD_BOOK, MAX_RECORD_BOOK + 1);
        return new Student.Builder()
                .groupNumber(group)
                .averageGrade(grade)
                .recordBookNumber(recordBook)
                .build();
    }
}