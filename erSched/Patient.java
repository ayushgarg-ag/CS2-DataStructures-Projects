package cs2.erSched;

public class Patient implements Comparable<Patient> {
    private String name;
    private Condition condition;

    public Patient(String n, Condition c) {
        name = n;
        condition  = c;
    }

    public String getName() {
        return name;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition con) {
        condition = con;
    }

    public int compareTo(Patient other) {
        return condition.compareTo(other.condition);
    }

    public String toString() {
        return name + " is in " + condition + " condition";
    }
}
