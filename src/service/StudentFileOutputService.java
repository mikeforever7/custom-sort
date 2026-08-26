package service;

import model.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentFileOutputService {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public void appendStudents(
            String filePath,
            String operationName,
            List<Student> students
    ) {
        Path path = Path.of(filePath);

        StringBuilder content = new StringBuilder();

        for (Student student : students) {
            content.append(student);
            content.append(System.lineSeparator());
        }

        try {
            Files.writeString(
                    path,
                    content.toString(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println("Результат сохранён в файл: "
                    + path.toAbsolutePath());

        } catch (IOException exception) {
            System.out.println("Ошибка записи в файл: "
                    + exception.getMessage());
        }
    }
}