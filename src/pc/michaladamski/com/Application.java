package pc.michaladamski.com;

import java.util.ArrayDeque;
import java.util.Deque;

public class Application {
    public final static int CAPACITY = 20;
    public final static int PROD_COUNT = 120;
    public final static int CONS_COUNT = 4;

    public static void main(String[] args) {
        Deque<Task> queue = new ArrayDeque<>(CAPACITY);
        TaskProducer taskProducer = new TaskProducer(queue);
        TaskConsumer taskConsumer = new TaskConsumer(queue);
        runThreads(taskProducer, PROD_COUNT);
        runThreads(taskConsumer, CONS_COUNT);
    }

    public static void runThreads(Runnable runnable, int count) {
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
