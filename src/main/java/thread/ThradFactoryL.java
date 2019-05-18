package thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThradFactoryL {

    /**
     * 线程池
     * */
    static class ThreadFactoryC implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup threadGroup; //线程组
        private AtomicInteger threadNumber = new AtomicInteger(1);
        String namePre; //名字前缀

        ThreadFactoryC() {
            SecurityManager s = System.getSecurityManager();
            threadGroup =
                    (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePre = "pool_" + poolNumber.getAndIncrement() + "-thread_";

        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(threadGroup, r, namePre + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    public static void main(String[] args) {
        ThreadFactoryC factory = new ThreadFactoryC();
        Thread thread1 = factory.newThread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("i=" + i);
                }
            }
        });

        Thread thread2 = factory.newThread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("j=" + i);
                }
            }
        });
        System.out.println(thread1.getName());
        System.out.println(thread2.getName());
        thread1.start();
        thread2.start();
    }


}
