import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    private static final int PAUSE = 1000;

    public static void main(String[] args) throws InterruptedException {
        Maps maps = new Maps();
        Maps.map = new ConcurrentHashMap<>();
        new Thread(null, maps::putTime, "ConcurrentHashMap").start();
        new Thread(null, maps::readTime, "ConcurrentHashMap").start();
        Thread.sleep(PAUSE);
        Maps.map = Collections.synchronizedMap(new HashMap<>());
        new Thread(null, maps::putTime, "SynchronizedMap").start();
        new Thread(null, maps::readTime, "SynchronizedMap").start();

        // Рейтинг скорости выполнения задач:
        // 1. Чтение мапы в ConcurrentHashMap;
        // 2. Чтение мапы в SynchronizedMap;
        // 3. Запись мапы в SynchronizedMap;
        // 4. Запись мапы в ConcurrentHashMap;
        // При увеличении размера списка тенденция сохраняется.
    }
}
