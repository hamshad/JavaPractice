import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class MemoizationPrac {
    Scanner sc = new Scanner(System.in);
    private static final float PIE = 3.1413f;
    long a;

    public MemoizationPrac() {
        a = sc.nextLong();
    }

    Map<Long, Double> cache = new ConcurrentHashMap<>();

    public Double memoFunc() {
        return cache.computeIfAbsent(a,
                (x) -> Double.parseDouble(String.valueOf(Float.parseFloat(String.valueOf(x)) * PIE)));
    }
}
