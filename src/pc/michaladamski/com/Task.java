package pc.michaladamski.com;

public class Task {
    private int x;
    private int y;

    public Task(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int execute() {
        return x + y;
    }
}
