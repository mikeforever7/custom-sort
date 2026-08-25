package model;

import java.util.Objects;

public class Student {

    private int groupNumber;
    private double averageGrade;
    private int recordBookNumber;

    public Student(int groupNumber, double averageGrade, int recordBookNumber) {
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.recordBookNumber = recordBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageGrade=" + String.format("%.2f", averageGrade) +
                ", recordBookNumber=" + recordBookNumber +
                '}';
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    public static class Builder {
        private int groupNumber;
        private double averageGrade;
        private int recordBookNumber;

        public Builder groupNumber(int groupNumber) {
            if (groupNumber < 1 || groupNumber > 100) {
                throw new IllegalArgumentException("Номер группы должен быть в диапазоне от 1 до 100 включительно");
            }
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageGrade(double averageGrade) {
            if (averageGrade < 0 || averageGrade > 10) {
                throw new IllegalArgumentException("Средний балл должен быть в диапазоне от 0 до 10 включительно ");
            }
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder recordBookNumber(int recordBookNumber) {
            if (recordBookNumber < 1 || recordBookNumber > 1000000) {
                throw new IllegalArgumentException("Номер зачетной книжки должен быть в диапазоне от 1 до 1000000 включительно");
            }
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(groupNumber, averageGrade, recordBookNumber);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return groupNumber == student.groupNumber
                && Double.compare(averageGrade, student.averageGrade) == 0
                && recordBookNumber == student.recordBookNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, averageGrade, recordBookNumber);
    }
}
