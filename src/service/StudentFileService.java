package service;

import collection.AwesomeArrayList;
import model.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class StudentFileService {

    public List<Student> readStudents(String filePath) {
        List<Student> students = new AwesomeArrayList();
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            System.out.println("Файл не найден: " + filePath);
            return students;
        }

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> addStudentFromLine(line, students));

        } catch (IOException exception) {
            System.out.println("Ошибка чтения файла: " + exception.getMessage());
        }

        return students;
    }

    private void addStudentFromLine(String line, List<Student> students) {
        try {
            Student student = parseStudent(line);
            students.add(students.size(), student);
        } catch (IllegalArgumentException exception) {
            System.out.println("Строка пропущена: " + line);
            System.out.println("Причина: " + exception.getMessage());
        }
    }

    private Student parseStudent(String line) {
        String[] values = line.split(";", -1);

        if (values.length != 3) {
            throw new IllegalArgumentException(
                    "Неверный формат. Ожидается: номерГруппы;среднийБалл;номерЗачётнойКнижки"
            );
        }

        int groupNumber = parseInteger(values[0].trim(), "Номер группы");
        double averageGrade = parseDouble(values[1].trim(), "Средний балл");
        int recordBookNumber = parseInteger(
                values[2].trim(),
                "Номер зачётной книжки"
        );

        return new Student.Builder()
                .groupNumber(groupNumber)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }

    private int parseInteger(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    fieldName + " должен быть целым числом"
            );
        }
    }

    private double parseDouble(String value, String fieldName) {
        try {
            return Double.parseDouble(value.replace(',', '.'));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    fieldName + " должен быть числом"
            );
        }
    }
}