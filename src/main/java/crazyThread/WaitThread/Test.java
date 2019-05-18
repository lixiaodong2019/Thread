package crazyThread.WaitThread;

public class Test {

    public static void main(String[] args) {
        Account account = new Account("帐号", 1000, false);
        Thread get = new Thread(null, () -> {
            for (int i = 0; i <3; i++) {
                account.draw(800);
            }
        }, "A用户");
        Thread set = new Thread(null, () -> {
            for (int i = 0; i <3; i++) {
                account.put(800);
            }
        }, "B用户");
        get.start();
        set.start();
    }
}
