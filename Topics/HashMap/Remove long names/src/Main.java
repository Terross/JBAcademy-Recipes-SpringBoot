import java.util.*;


class MapFunctions {

    public static void removeLongNames(Map<String, String> map) {
        // write your code here
        ArrayList<String> removeKeys = new ArrayList<>();
        for (Map.Entry<String, String> elem:
             map.entrySet()) {
            if (elem.getValue().length() > 7 || elem.getKey().length() > 7) {
                removeKeys.add(elem.getKey());
            }
        }
        for (String key:
             removeKeys) {
            map.remove(key);
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
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.removeLongNames(map);

        System.out.println(map.size());
    }
}