import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(" ");

        String symbol = scanner.next();
        int count = 0;
        for (String str:
             strings) {
            if (str.equals(symbol)) {
                count++;
            }
        }

        System.out.println(count);
    }
}