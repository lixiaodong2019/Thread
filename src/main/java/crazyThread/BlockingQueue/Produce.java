package crazyThread.BlockingQueue;


import java.util.concurrent.BlockingQueue;

/**
 * 生产者线程
 * */
public class Produce extends Thread {

    private BlockingQueue<String> queue;

    public Produce(BlockingQueue<String> queue, String s) {
        super(s);
        this.queue = queue;
    }

    @Override
    public void run() {
        String[] s = new String[]{"C++", "java", "python"};
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "生产者准备生产集合元素"+queue);
            try {
                Thread.sleep(200);
                queue.put(s[i % 3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产完成" + queue);
        }
    }
}
