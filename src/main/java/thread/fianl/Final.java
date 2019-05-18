package thread.fianl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Final {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        Map<Integer, Integer> m = Collections.unmodifiableMap(map);
        System.out.println(m.get(1));
    }


}
