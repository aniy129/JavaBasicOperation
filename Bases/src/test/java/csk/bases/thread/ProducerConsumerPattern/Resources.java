package csk.bases.thread.ProducerConsumerPattern;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

public class Resources {
    private ArrayBlockingQueue<Datas> queue;
    private int max;

    public int getMax() {
        return max;
    }


    public Resources(int max) {
        this.queue = new ArrayBlockingQueue<>(100);
        this.max = max;
    }

    public synchronized void add() {
        if (getQueueLength() <= getMax()) {
            Datas datas = new Datas("resource-" + Thread.currentThread().getId(), Math.random(), new Date());
            queue.add(datas);
            System.out.println("生产一个资源 " + datas.toString());
            notifyAll();
        } else {
            try {
                System.out.println("资源充足，等待消费");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void remove() {
        if (getQueueLength() > 0) {
            Datas dt = queue.remove();
            System.out.println("消费一个资源 " + dt.toString());
            notifyAll();
        } else {
            try {
                System.out.println("资源不足，等待生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getQueueLength() {
        return queue.size();
    }

}
