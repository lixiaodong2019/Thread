package crazyThread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Print {
    /**
     * 需求:用两个线程 1个打印1-52,另一个打印A-Z
     *12A34B....5152Z
     * */
    private int count; //记录数字打印的次数
    private int num;   //打印的数字
    private char word;//打印的字母
    private Lock lock;
    private Condition condition;

    public Print(int count, int num, char word, Lock lock) {
        this.count = count;
        this.num = num;
        this.word = word;
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    private void printNumber() {
        lock.lock();
        while (num <= 52) {
            if (count == 2) { //当数量等于2的时候,暂停线程,释放锁
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(num++);
                count++;
                condition.signal(); //唤醒其他暂停的线程
            }
        }
        lock.unlock();
    }

    private void printWord() {
        lock.lock();
        while (word <= 'Z') {
            if (count != 2) { //当计时器不为2的时候,暂停等待
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(word++);
                count = 0;
                condition.signal();
            }
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        Print print = new Print(0, 1, 'A', new ReentrantLock());
        new Thread(print::printNumber).start();
        new Thread(print::printWord).start();
    }
}
