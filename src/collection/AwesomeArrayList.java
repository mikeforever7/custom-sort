package collection;

import model.Student;

import java.util.AbstractList;

public class AwesomeArrayList extends AbstractList<Student> {
    private Student[] myArray;
    private int arraySize;
    private static final int DEFAULT_ARRAY_SIZE = 10;

    public AwesomeArrayList() {
        this.myArray = new Student[DEFAULT_ARRAY_SIZE];
        this.arraySize = 0;
    }

    @Override
    public int size() {
        return arraySize;
    }

    //добавление всегда в конец
    @Override
    public void add(int index, Student element) {
        if (index < 0 || index > arraySize) {
            throw new IndexOutOfBoundsException("такого элемента в этом потрясающем массиве нет");
        }
        if (arraySize == myArray.length) {
            int TEMP_ARRAY_SIZE = arraySize * 2;
            Student[] tempArray = new Student[TEMP_ARRAY_SIZE];
            for (int i = 0; i < arraySize; i++) {
                tempArray[i] = myArray[i];
            }
            myArray = tempArray;
        }
        myArray[arraySize] = element;
        arraySize++;
    }

    @Override
    public Student get(int index) {
        if (index < 0 || index >= arraySize) {
            throw new IndexOutOfBoundsException("такого элемента в этом потрясающем массиве нет");
        }
        return myArray[index];
    }

    //удаления нет предусмотрено в программе, но на всякий случай реализовал
    @Override
    public Student remove(int index) {
        if (index < 0 || index >= arraySize) {
            throw new IndexOutOfBoundsException("такого элемента в этом потрясающем массиве нет");
        }
        Student removed = myArray[index];
        for (int i = index; i < arraySize - 1; i++) {
            myArray[i] = myArray[i + 1];
        }
        myArray[arraySize - 1] = null;
        arraySize--;
        return removed;
    }
}
