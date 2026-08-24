package strategy;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

public class SelectiveSort {
    public List<Student> sort(List<Student> students, ToIntFunction<Student> fieldExtractor) {
        List<Student> resultList = new ArrayList<>(students);

        for (int i = 0; i < resultList.size(); i++) {
            if (fieldExtractor.applyAsInt(resultList.get(i)) % 2 != 0) {
                continue;
            }
            for (int j = i + 1; j < resultList.size(); j++) {
                if (fieldExtractor.applyAsInt(resultList.get(j)) % 2 != 0) {
                    continue;
                }
                if (fieldExtractor.applyAsInt(resultList.get(i)) > fieldExtractor.applyAsInt(resultList.get(j))) {
                    Student temp = resultList.get(j);
                    resultList.set(j, resultList.get(i));
                    resultList.set(i, temp);
                }
            }
        }
        return resultList;
    }
}
