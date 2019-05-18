package thread.delayQueue;


import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 消息体定义 实现Delayed接口就是实现两个方法即compareTo 和 getDelay最重要的就是getDelay方法，这个方法用来判断是否到期……
 *
 * @author whd
 * @date 2017年9月24日 下午8:57:14
 */
public class Message implements Delayed {
    private int id;
    private String body; // 消息内容
    private long excuteTime;// 延迟时长，这个是必须的属性,因为要按照这个判断延时时长。

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public Message(int id, String body, long delayTime) {
        this.id = id;
        this.body = body;
        this.excuteTime =
                TimeUnit.MILLISECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
    }

    // 自定义实现比较方法返回 1 0 -1三个参数
    public int compareTo(Delayed delayed) {
        Message msg = (Message) delayed;
        return this.id > msg.id ? 1 :
                (this.id < msg.id ? -1 : 0);
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期

    public long getDelay(TimeUnit unit) {
        return unit.convert(this.excuteTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
}


