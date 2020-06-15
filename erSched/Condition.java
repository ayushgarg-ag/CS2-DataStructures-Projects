package cs2.erSched;

public class Condition implements Comparable<Condition> {
    static public final Condition critical = new Condition(1, "critical");
    static public final Condition serious = new Condition(2, "serious");
    static public final Condition fair = new Condition(3, "fair");

    private int priority;
    private String name;

    public Condition(int prty, String nm) {
        name = nm;
        priority = prty;
    }

    public int compareTo(Condition other) {
        return priority - other.priority;
    }

    public String toString() {
        return name;
    }
}
