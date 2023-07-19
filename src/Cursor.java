public class Cursor {

    private int x;
    private int y;

    public Cursor() {
        this(0, 0);
    }

    public Cursor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveDown() {
        move(0, 1);
    }

    public void moveUp() {
        move(0, -1);
    }

    public void moveLeft() {
        move(-1, 0);
    }

    public void moveRight() {
        move(1, 0);
    }

}
