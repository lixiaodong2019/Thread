package crazyThread.lock;

import java.util.concurrent.locks.Lock;

public class Account {
    private String name; //编号
    private double count; //余额
    private final Lock lock;

    public Account(String num, double count, Lock lock) {
        this.name = num;
        this.count = count;
        this.lock = lock;
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
        System.out.println(Thread.currentThread().getName() + "取钱中。。。。");
        //加锁
        lock.lock();
        try {
            if (count > drawCount) {
                System.out.println(Thread.currentThread().getName() + "取钱" + drawCount + "成功！");
                count -= drawCount;
                System.out.println("余额为" + getCount());
            } else {
                System.out.println(Thread.currentThread().getName() + "取钱失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }


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
}
