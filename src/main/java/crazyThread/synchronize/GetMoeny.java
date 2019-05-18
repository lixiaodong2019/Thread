package crazyThread.synchronize;


public class GetMoeny {
    /**
     * 银行取钱
     * 步骤:1,输入金额
     * 2,判断是否能够取出
     *
     * 解决这个问题,需要加入同步监视器
     * 线程在执行同步代码块之前，先获得对同步监视器的锁定
     *
     * */

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account("帐号", 1000);
        new Thread(null, () -> {
            account.draw(800, true);
        }, "A用户").start();
        Thread.sleep(10);//让A线程先运行,得到我们想要的结果
        new Thread(null, () -> {
            account.draw(800, false);
        }, "B用户").start();
        new Thread(null, () -> {
            account.draw(800, true);
        }, "C用户").start();
        new Thread(null, () -> {
            account.draw(800, true);
        }, "D用户").start();
    }
}
