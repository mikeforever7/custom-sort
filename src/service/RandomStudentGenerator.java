package service;

import java.util.Random;

public class RandomStudentGenerator {
    //TODO Эти диапазоны должны совпадать с будущей валидацией Builder
    private static final int MIN_GROUP = 1;
    private static final int MAX_GROUP = 100;
    private static final int MIN_GRADE = 1;
    private static final int MAX_GRADE = 10;
    private static final int MIN_RECORD_BOOK = 1;
    private static final int MAX_RECORD_BOOK = 1000000;

    private final Random random = new Random();

}
