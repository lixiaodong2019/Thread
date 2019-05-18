package crazyThread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockThread {
    /**
     *同步锁
     * */
    public static void main(String[] args) {
        Account account = new Account("帐号", 1000, new ReentrantLock());
        new Thread(null, () -> {
            account.draw(800);
        }, "A用户").start();
        new Thread(null, () -> {
            account.draw(800);
        }, "B用户").start();
    }
}
