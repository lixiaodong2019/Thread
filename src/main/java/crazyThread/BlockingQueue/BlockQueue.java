package crazyThread.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class BlockQueue {
    public static void main(String[] args) throws InterruptedException {
/*        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.put("s");
        bq.put("s");
        bq.put("s"); //阻塞线程*/

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        new Produce(queue, "生产者1").start();
        new Produce(queue, "生产者2").start();
        new Consumer(queue, "消费者").start();
    }
}
