package pc.michaladamski.com;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import static pc.michaladamski.com.Application.CAPACITY;

public class TaskProducer implements Runnable {
    private static final int BOUND = 1000;
    private BlockingDeque<Task> queue;
    private Random random = new Random();
    private volatile boolean produce = true;

    public TaskProducer(BlockingDeque<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        while (!interrupted) {
            Task task = new Task(random.nextInt(BOUND), random.nextInt(BOUND));
            try {
                if (produce) {
                    queue.put(task);
                    if (queue.remainingCapacity() == 0) {
                        produce = false;
                    }
                } else {
                    Thread.sleep(100);
                    produce = queue.remainingCapacity() >= CAPACITY / 2;
                }
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
    }
}
