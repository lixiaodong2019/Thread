package crazyThread.WaitThread;

public class Print {
    /**
     * 需求:用两个线程 1个打印1-52,另一个打印A-Z
     *12A34B....5152Z
     * */
    private int count = 0; //记录数字打印的次数
    private int num = 1;   //打印的数字
    private char word = 'A';//打印的字母

    private synchronized void printNumber() {
        while (num <= 52) {
            if (count == 2) { //当数量等于2的时候,暂停线程,释放锁
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(num++);
                count++;
                notify(); //唤醒其他暂停的线程
            }
        }
    }

    private synchronized void printWord() {
        while (word <= 'Z') {
            if (count != 2) { //当计时器不为2的时候,暂停等待
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(word++);
                count = 0;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        Print print = new Print();
        new Thread(print::printNumber).start();
        new Thread(print::printWord).start();
    }
}
