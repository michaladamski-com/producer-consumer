package pc.michaladamski.com;

import java.util.Deque;

public class TaskConsumer implements Runnable {
    private Deque<Task> queue;

    public TaskConsumer(Deque<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        while (!interrupted) {
            synchronized (queue) {
                try {
                    if (queue.isEmpty()) {
                        queue.notifyAll();
                        queue.wait();
                    } else {
                        System.out.println("Task execution result: " + queue.poll().execute());
                        if (queue.size() < Application.CAPACITY / 2) {
                            queue.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        }
    }
}
