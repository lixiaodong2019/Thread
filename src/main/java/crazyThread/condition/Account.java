package crazyThread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private String name; //编号
    private double count; //余额
    private boolean isEmpty;
    private Lock lock;
    private Condition condition;

    public Account(String num, double count, boolean isEmpty, Lock lock) {
        this.name = num;
        this.count = count;
        this.isEmpty = isEmpty;
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    public String getNum() {
        return name;
    }

    /**
     * 因为账户余额不可随意修改,所以只提供getter方法
     * */
    public double getCount() {
        return count;
    }

    public void draw(double drawCount) {
        lock.lock();
        if (isEmpty) {
            try {
                condition.await();//暂停当前线程,并且释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if (count > drawCount) {
                count -= drawCount;
                System.out.println(
                        Thread.currentThread().getName() + "取了" + drawCount + "元,目前余额为:" +
                                getCount());
                isEmpty = true;
                condition.signal();
            }
        }
        lock.unlock();
    }

    /**
     *程序执行到最后可能会出现阻塞情况,会有存钱或者取钱的线程出现暂停，等待取钱或者存钱的线程来唤醒
     * */
    public synchronized void put(double putCount) {
        lock.lock();
        if (!isEmpty) {
            try {
                condition.await(); //暂停当前线程,并且释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            count += putCount;
            System.out.println(
                    Thread.currentThread().getName() + "存了" + putCount + "元,目前余额为:" + getCount());
            isEmpty = false;
            condition.signal();
        }
        lock.unlock();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && o.getClass() == Account.class) {
            Account target = (Account) o;
            return target.getNum().equals(name);
        }
        return false;
    }

    public static void main(String[] args) {
        Account account = new Account("10086", 1000, true, new ReentrantLock());
        new Thread(null, () -> {
            for (int i = 0; i < 7; i++) {
                account.draw(800);
            }
        }, "A客户").start();
        new Thread(null, () -> {
            for (int i = 0; i < 7; i++) {
                account.put(800);
            }
        }, "B客户").start();
    }
}
