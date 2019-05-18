package crazyThread;

public class YieldThread extends Thread {
    private boolean l;
    public YieldThread(String name,boolean h){
        super(name);
        this.l = h;
    }
    public void run(){
        for (int i = 0;i<100;i++){
            System.out.println(getName()+" "+i);
            if (i==2&&l){
                yield();
            }

        }}

    public static void main(String[] args) {
        Thread h = new YieldThread("高",false);
        h.setPriority(Thread.MAX_PRIORITY);
        Thread l = new YieldThread("低",true);
        l.setPriority(Thread.MIN_PRIORITY);
        h.start();
        l.start();
    }
}
