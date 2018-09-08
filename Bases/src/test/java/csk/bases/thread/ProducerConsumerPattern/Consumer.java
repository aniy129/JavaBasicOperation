package csk.bases.thread.ProducerConsumerPattern;

public class Consumer implements Runnable {
    private Resources resources;

    public Consumer(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resources.remove();
        }
    }
}
