import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        // write your code here
        int count = 0;
        for (Map.Entry<String, String> elem1:
                map1.entrySet()) {
            for (Map.Entry<String, String> elem2:
                 map2.entrySet()) {
                if (elem1.getKey().equals(elem2.getKey()) && elem1.getValue().equals(elem2.getValue())) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}