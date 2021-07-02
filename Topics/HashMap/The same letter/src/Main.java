import java.util.*;
import java.util.stream.Stream;


class MapFunctions {

    public static void printWithSameLetter(Map<String, String> map) {
        // write your code here
        map.entrySet().removeIf(e -> e.getKey().charAt(0) != e.getValue().charAt(0));
        for (Map.Entry<String, String> elem:
             map.entrySet()) {
            System.out.println(elem.getKey() + " " + elem.getValue());
        }
    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.length() < 1) {
                break;
            }
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.printWithSameLetter(map);
    }
}