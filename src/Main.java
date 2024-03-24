import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;
import static java.util.concurrent.Executors.newFixedThreadPool;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //private static Integer val = 0;
    private static AtomicInteger val = new AtomicInteger(0);
    private static ConcurrentHashMap<Integer, Integer> hm = new ConcurrentHashMap<>();
    //private static HashMap<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        ExecutorService es = newFixedThreadPool(2);
        for (int i = 0; i < 1000; i++) {

            //es.submit(()->{synchronized(Main.class){Main.val++;}});
            es.submit(() -> {
                Main.val.getAndIncrement();
            });
        }


        sleep(3000);
        System.out.println(Main.val);
        es.close();


//======================================================
        Main.hm.put(405, 600);
        Main.hm.put(406, 1600);
        Main.hm.put(407, 5000);
        Main.hm.put(408, 100);
        Main.hm.forEach((k, v) -> System.out.println(k + "  " + v));
        float sum = (float) hm.values().stream().reduce(0, (x, y) -> x + y);
        System.out.println("sum=" + sum);

        ExecutorService es2 = newFixedThreadPool(20);
        for (int i = 0; i < 1000; i++) {
            System.out.println("Transaction");
            Random rnd = new Random();

            int count = Main.hm.size();
            Integer elem = 405+rnd.nextInt(count);
            Integer elem2 = 405+rnd.nextInt(count);
            Integer val = rnd.nextInt(Main.hm.get(elem)/50);
            System.out.printf("Transaction from %d to %d with value %d\n", elem, elem2, val);

            es2.submit(() -> {
                   Main.hm.put(elem, (Main.hm.get(elem) - val));
                     });
            es2.submit(() -> {
                 Main.hm.put(elem2, (Main.hm.get(elem2) + val));

            });
        }

        sleep(5000);
        es2.close();
        Main.hm.forEach((k, v) -> System.out.println(k + "  " + v));
        sum = (float) hm.values().stream().reduce(0, (x, y) -> x + y);
        System.out.println("sum after day=" + sum);

//        ExecutorService es3 = newFixedThreadPool(10);
//        for (int i = 0; i < 100; i++) {
//            int finalI = i;
//            es3.submit(() -> {
//                System.out.println(finalI);
//            });
//
//        }
    }
}
