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
    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }


        ExecutorService es = newFixedThreadPool(2);
        for (int i = 0; i < 1000; i++) {

            //es.submit(()->{synchronized(Main.class){Main.val++;}});
            es.submit(()->{Main.val.getAndIncrement();});
        }

        sleep(3000);
        System.out.println(Main.val);


    }
}