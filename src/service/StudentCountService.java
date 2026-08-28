package service;

import model.Student;
import java.util.List;
import java.util.Objects;

public class StudentCountService {

    public int countOccurrences(List<Student> students, Student target) {
        int middle = students.size() / 2;

        int[] counts = new int[2];

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < middle; i++) {
                if (Objects.equals(students.get(i), target)) {
                    counts[0]++;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = middle; i < students.size(); i++) {
                if (Objects.equals(students.get(i), target)) {
                    counts[1]++;
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return counts[0] + counts[1];
    }
}
