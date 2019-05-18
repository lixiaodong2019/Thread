package crazyThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * futureTask包含callable接口对象,以及实现了runnable接口
         * 重写的run方法里面调用了callable对象的call方法
         * */
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            int i = 0;

            @Override
            public Integer call() {
                for (; i < 1000; i++) {
                    System.out.println(Thread.currentThread().getName() + "--" + i);
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
