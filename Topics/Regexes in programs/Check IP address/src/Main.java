import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        String str = (new Scanner(System.in)).nextLine();
        String regStr = "(([0-9]|[0-9][0-9]|1[0-9][0-9]|2[0-5][0-5])\\.){3}([0-9]|[0-9][0-9]|1[0-9][0-9]|2[0-5][0-5])";
        System.out.println(str.matches(regStr) ? "YES" : "NO");
    }
}