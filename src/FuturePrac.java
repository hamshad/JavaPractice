import java.util.concurrent.*;

public class FuturePrac {

    String str = "Ummm";
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    FuturePrac() {
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000l);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Hello World";
        });

        System.out.println("Processing...");

        // try {
        // str = future.get(5, TimeUnit.SECONDS);
        // System.out.println("Its taking too much time!!");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        try {
            str = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(str);
    }
}
