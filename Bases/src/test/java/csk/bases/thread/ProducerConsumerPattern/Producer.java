package csk.bases.thread.ProducerConsumerPattern;

public class Producer implements Runnable {
    private Resources resources;
    private boolean turnOff;

    public Producer(Resources resources) {
        this.resources = resources;
        this.turnOff = true;
    }

    public void turnOff() {
        turnOff=false;
    }

    @Override
    public void run() {
        while (turnOff) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resources.add();
        }
        System.out.printf("%s停止生产\r\n",Thread.currentThread().getId());
    }
}
