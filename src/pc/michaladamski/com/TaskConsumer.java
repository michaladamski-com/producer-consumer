package pc.michaladamski.com;

import java.util.Deque;
import java.util.Optional;

public class TaskConsumer implements Runnable {
    private Deque<Task> queue;

    public TaskConsumer(Deque<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        while (!interrupted) {
            Optional<Task> task = Optional.empty();
            synchronized (queue) {
                try {
                    if (queue.isEmpty()) {
                        queue.notifyAll();
                        queue.wait();
                    } else {
                        task = Optional.of(queue.poll());
                        if (queue.size() < Application.CAPACITY / 2) {
                            queue.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
            task.ifPresent(t -> System.out.println("Task execution result: " + t.execute()));
        }
    }
}
