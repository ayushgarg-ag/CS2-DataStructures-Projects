package cs2.queue;

public class Student implements Comparable<Student> {
    private String name;
    private int age;
    public static final int ASC = 0;
    public static final int DESC = 1;
    private static int dir;

    public Student(String n, int a) {
        name = n;
        age = a;
    }

    public static void setDir(int dir) {
        Student.dir = dir;
    }

    public String toString() {
        return "Name: " + name + " Age: " + age;
    }

    public int compareTo(Student other) {
        if (this.age < other.age) {
            if (dir == 1) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.age > other.age) {
            if (dir == 1) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if (this.name.compareTo(other.name) > 0) {
                return 1;
            } else if (this.name.compareTo(other.name) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
