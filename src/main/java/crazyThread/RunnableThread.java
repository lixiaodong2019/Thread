package crazyThread;

public class RunnableThread implements Runnable {
    private  int count = 1000;

    @Override
    public void run() {
        for (; count > 0; count--) {
            System.out.println(Thread.currentThread().getName()+ "-->"+count);
        }
    }

    public static void main(String[] args) {
        RunnableThread target = new RunnableThread();
        Thread thread1 = new Thread(target, "1");
        Thread thread2 = new Thread(target, "2");
        thread1.start();
        thread2.start();
    }
}
