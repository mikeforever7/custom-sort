import service.SortService;
import service.StudentInputService;
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
        StudentInputService studentInputService = new StudentInputService();
        ConsoleMenu consoleMenu = new ConsoleMenu(scanner, sortService,studentInputService);
        consoleMenu.run();
    }
}