package service;

import collection.AwesomeArrayList;
import model.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentInputService {
    private final StudentFileService studentFileService;

    public StudentInputService(StudentFileService studentFileService) {
        this.studentFileService = studentFileService;
    }

    public List<Student> inputManualStudents(int manualCount) {
        FillStrategy manualStrategy = new ManualStudentInput();
        return Stream.generate(manualStrategy::fill)
                .limit(manualCount)
                .collect(Collectors.toCollection(AwesomeArrayList::new));
    }

    public List<Student> inputRandomStudents(int manualCount) {
        FillStrategy randomStrategy = new RandomStudentGenerator();
        return Stream.generate(randomStrategy::fill)
                .limit(manualCount)
                .collect(Collectors.toCollection(AwesomeArrayList::new));
    }

    public List<Student> inputFromFileStudents(String filePath) {
        return studentFileService.readStudents(filePath);
    }
}