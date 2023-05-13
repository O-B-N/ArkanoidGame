package game;
// ID: 209083682

/**
 * counter class.
 */
public class Counter {
    private int count = 0;

    /**
     * creates a counter object.
     */
    public Counter() {
    }

    /**
     * creates a counter object.
     * @param number start with this number
     */
    public Counter(int number) {
        count = number;
    }

    /**
     * add number to current count.
     * @param number increase the count by number
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     * @param number  decrease the count by number
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * get current count.
     * @return the count
     */
    public int getValue() {
        return count;
    }
}