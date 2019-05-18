package crazyThread.synchronize;

public class Account {
    private String name; //编号
    private double count; //余额

    public Account(String num, double count) {
        this.name = num;
        this.count = count;
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

    public synchronized void draw(double drawCount, boolean delay) {
        System.out.println(Thread.currentThread().getName() + "取钱中。。。。");
        if (delay) {
            // 当前线程执行同步代码块或者方法的时候，
            // 程序执行了同步监听器对象的wait方法,则当前线程暂停,并且释放同步监听器。
            try {
                System.out.println(Thread.currentThread().getName() + "请耐心等待3秒");
                this.wait(); //释放锁,线程暂停,这个时候另一个线程就会获得锁
                /**
                 * 当线程在获取锁后处于阻塞状态时,其他线程也同样处于阻塞状态,等待获取同步监听器
                 * */
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.notifyAll();//唤醒其他暂停的线程程
            //            this.notify();//随机唤醒某一个暂停的线程
        }
        if (count > drawCount) {
            System.out.println(Thread.currentThread().getName() + "取钱" + drawCount + "成功！");
            count -= drawCount;
            System.out.println("余额为" + getCount());
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！");
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
