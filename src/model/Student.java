package model;

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
                ", averageGrade=" + averageGrade +
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
}
