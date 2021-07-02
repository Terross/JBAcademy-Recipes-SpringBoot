import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        String password = (new Scanner(System.in)).nextLine();
        String reg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{12,}$";

        System.out.println(password.matches(reg) ? "YES" : "NO");


    }
}