package crazyThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTaskThread thread = new FutureTaskThread();
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            int i = 0;

            @Override
            public Integer call() {
                for (; i < 1000; i++) {
                    System.out.println(Thread.currentThread().getName()+"--" + i);
                }
                return i;
            }
        });

        Thread thread1 = new Thread(task, "A线程");
        Thread thread2 = new Thread(task, "B线程");
        thread1.start();
        thread2.start();
        System.out.println(task.get());
    }
}
