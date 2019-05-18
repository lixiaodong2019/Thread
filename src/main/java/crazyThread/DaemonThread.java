package crazyThread;
/**
 * 在主线程执行完毕后守护线程自动结束
 * */
public class DaemonThread extends Thread {
    public DaemonThread(String name){
        super(name);
    }
    public void run(){
        for (int i = 0;i<1000;i++){
            System.out.println(getName()+" "+i);
        }
    }

    public static void main(String[] args) {
    DaemonThread DT = new DaemonThread("守护线程");
    DT.setDaemon(true);//设置守护线程必须在start方法运行前
    DT.start();
    for (int i =0;i<10;i++){
        System.out.println(Thread.currentThread().getName()+" "+i);
    }
    }
}
