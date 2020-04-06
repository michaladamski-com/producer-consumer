package pc.michaladamski.com;

import java.util.Deque;
import java.util.Random;

public class TaskProducer implements Runnable {
    private static final int BOUND = 1000;
    private Deque<Task> queue;
    private Random random = new Random();

    public TaskProducer(Deque<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        while (!interrupted) {
            synchronized (queue) {
                try {
                    // add task to queue until it not reach capacity
                    if (queue.size() < Application.CAPACITY) {
                        Task task = new Task(random.nextInt(BOUND), random.nextInt(BOUND));
                        queue.offer(task);
                    } else {
                        queue.notifyAll();
                        queue.wait();
                    }
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        }
    }
}
