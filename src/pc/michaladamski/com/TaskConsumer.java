package pc.michaladamski.com;

import java.util.concurrent.BlockingDeque;

public class TaskConsumer implements Runnable {
    private BlockingDeque<Task> queue;

    public TaskConsumer(BlockingDeque<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean interrupted = false;
        while (!interrupted) {
            Task task = null;
            try {
                task = queue.take();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupted = true;
            }
            System.out.println("Task execution result: " + task.execute());
        }
    }
}
