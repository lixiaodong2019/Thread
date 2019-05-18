package crazyThread;

public class JoinThread extends Thread{

    /**
     * main 0
     * main 1
     * 被join的线程 0
     * 被join的线程 1
     * 被join的线程 2
     * 被join的线程 3
     * 被join的线程 4
     * main 2
     * main 3
     * main 4
     * 执行完了被join的线程才开始执行主线程
     * */


    public JoinThread(String s) {
        super(s);
    }

public static void main(String[] args) throws InterruptedException {
    for (int i =0;i<5;i++){
        if (i==2){
            JoinThread l = new JoinThread("被join的线程");
            l.start();
            l.join();
        }
        System.out.println(Thread.currentThread().getName()+" "+i);
    }
}
    public void run(){
    for (int i = 0;i<5;i++){
        System.out.println(getName()+" "+i);
    }
}

}
