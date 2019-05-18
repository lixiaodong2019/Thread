package crazyThread.BlockingQueue;


import java.util.concurrent.BlockingQueue;

/**
 * 消费者线程
 * */
public class Consumer extends Thread {

    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue, String s) {
        super(s);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + "准备集合消费元素"+queue);
            try {
                Thread.sleep(200);
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费完成" + queue);
        }
    }
}
