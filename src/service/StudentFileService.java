package service;

import collection.AwesomeArrayList;
import model.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.StandardOpenOption;

public class StudentFileService {

    public List<Student> readStudents(String filePath) {
        List<Student> students = new AwesomeArrayList();

        try {
            Path path = getPath(filePath);

            if (!Files.exists(path)) {
                System.out.println("Файл не найден: " + path.toAbsolutePath());
                return students;
            }

            List<String> lines = Files.readAllLines(
                    path,
                    StandardCharsets.UTF_8
            );

            int lineNumber = 0;

            for (String line : lines) {
                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                try {
                    Student student = parseStudent(line);
                    students.add(students.size(), student);

                } catch (IllegalArgumentException exception) {
                    System.out.println(
                            "Строка №" + lineNumber + " пропущена: " + line
                    );
                    System.out.println("Причина: " + exception.getMessage());
                }
            }

            System.out.println("Из файла прочитано студентов: "
                    + students.size());

        } catch (InvalidPathException exception) {
            System.out.println("Некорректный путь к файлу: " + filePath);

        } catch (IOException exception) {
            System.out.println("Ошибка чтения файла: "
                    + exception.getMessage());
        }

        return students;
    }

    private Student parseStudent(String line) {
        String normalizedLine = line.trim();

        if (!normalizedLine.startsWith("Student{")
                || !normalizedLine.endsWith("}")) {
            throw new IllegalArgumentException(
                    "Ожидается формат: Student{groupNumber=..., "
                            + "averageGrade=..., recordBookNumber=...}"
            );
        }

        try {
            String content = normalizedLine
                    .replace("Student{", "")
                    .replace("}", "");

            String[] values = content.split(", ");

            if (values.length != 3) {
                throw new IllegalArgumentException(
                        "Не удалось прочитать все поля студента"
                );
            }

            int groupNumber = Integer.parseInt(
                    values[0].replace("groupNumber=", "").trim()
            );

            double averageGrade = Double.parseDouble(
                    values[1]
                            .replace("averageGrade=", "")
                            .trim()
                            .replace(',', '.')
            );

            int recordBookNumber = Integer.parseInt(
                    values[2]
                            .replace("recordBookNumber=", "")
                            .trim()
            );

            return new Student.Builder()
                    .groupNumber(groupNumber)
                    .averageGrade(averageGrade)
                    .recordBookNumber(recordBookNumber)
                    .build();

        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Номер группы, средний балл и номер зачётки должны быть числами"
            );
        }
    }

    private Path getPath(String filePath) {
        String normalizedPath = filePath.trim()
                .replaceAll("^\"|\"$", "");

        return Path.of(normalizedPath);
    }
    public void appendStudents(
            String filePath,
            String operationName,
            List<Student> students
    ) {
        try {
            Path path = getPath(filePath);

            StringBuilder content = new StringBuilder();


            content.append("Количество студентов: ").append(students.size());
            content.append(System.lineSeparator());

            for (Student student : students) {
                content.append(student);
                content.append(System.lineSeparator());
            }

            Files.writeString(
                    path,
                    content.toString(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println("Результат сохранён в файл: "
                    + path.toAbsolutePath());

        } catch (InvalidPathException exception) {
            System.out.println("Некорректный путь: " + filePath);

        } catch (IOException exception) {
            System.out.println("Ошибка записи файла: "
                    + exception.getMessage());
        }
    }
}