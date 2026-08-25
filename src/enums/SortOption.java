package enums;

import model.Student;

import java.util.function.ToIntFunction;

public enum SortOption {
    GROUP,
    AVERAGE_GRADE,
    RECORD_BOOK;

    public ToIntFunction<Student> getIntExtractor() {
        return switch (this) {
            case GROUP -> Student::getGroupNumber;
            case AVERAGE_GRADE -> null;
            case RECORD_BOOK -> Student::getRecordBookNumber;
        };
    }
}
