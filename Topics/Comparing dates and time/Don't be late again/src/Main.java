import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i ++) {
            String shop = scanner.next();
            String[] time = scanner.next().split(":");
            if (LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])).minusMinutes(30).isAfter(LocalTime.of(19,30))) {
                System.out.println(shop);
            }
        }
    }
}