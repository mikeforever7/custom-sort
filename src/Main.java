import service.SortService;
import service.StudentCountService;
import service.StudentInputService;
import service.StudentFileService;
import strategy.QuickSort;
import strategy.SelectiveSort;
import ui.ConsoleMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuickSort quickSort = new QuickSort();
        SelectiveSort selectiveSort = new SelectiveSort();
        SortService sortService = new SortService(quickSort, selectiveSort);
        StudentFileService studentFileService = new StudentFileService();
        StudentInputService studentInputService = new StudentInputService(studentFileService);
        StudentCountService studentCountService = new StudentCountService();
        ConsoleMenu consoleMenu = new ConsoleMenu(scanner, sortService, studentInputService, studentFileService, studentCountService);
        consoleMenu.run();
    }
}