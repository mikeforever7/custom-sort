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
                throw new IllegalArgumentException("groupNumber must be between 1 and 100");
            }

            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageGrade(double averageGrade) {
            if (averageGrade < 0 || averageGrade > 10) {
                throw new IllegalArgumentException("averageGrade must be between 0 and 10");
            }

            this.averageGrade = averageGrade;
            return this;
        }

        public Builder recordBookNumber(int recordBookNumber) {
            if (recordBookNumber < 1 || recordBookNumber > 1000000) {
                throw new IllegalArgumentException("recordBookNumber must be between 1 and 1000000");
            }

            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(groupNumber, averageGrade, recordBookNumber);
        }
    }
}
