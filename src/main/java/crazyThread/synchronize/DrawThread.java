package crazyThread.synchronize;

public class DrawThread extends Thread {
    private final Acount acount;
    private double drawCount;

    public DrawThread(String name, Acount acount, double drawCount) {
        super(name);
        this.acount = acount;
        this.drawCount = drawCount;
    }


    /**
     * 使用acount作为同步监视器,任何代码在进入同步代码块之前，都必须对account帐户进行锁定,
     * 其他线程无法获得锁，也就无法修改它
     *步骤:加锁-修改-释放锁
     * */
    public void run() {
        synchronized (acount){
            if (acount.getCount() >= drawCount) {
                System.out.println(getName() + "取钱" + drawCount + "成功！");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                acount.setCount(acount.getCount() - drawCount);
                System.out.println("余额为:" + acount.getCount());
            } else {
                System.out.println(getName()+"取钱失败!,余额不足");
            }
        }
    }

}
