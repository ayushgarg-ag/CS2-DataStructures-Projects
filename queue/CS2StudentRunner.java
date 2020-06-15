package cs2.queue;

public class CS2StudentRunner {

    public static void main(String[] args) {

        CS2PriorityQueue<Student> q = new CS2PriorityQueue<Student>();
        Student.setDir(Student.ASC);

        q.add(new Student("Julius Fleming", 16));
        q.add(new Student("Roberta Adkins", 16));
        q.add(new Student("Bernice Jefferson", 18));
        q.add(new Student("Gwen Malone", 17));
        q.add(new Student("Dustin Bowers", 17));
        q.add(new Student("Daisy Powell", 18));
        q.add(new Student("Ramona Briggs", 16));
        q.add(new Student("Kerry Dennis", 16));
        q.add(new Student("Colin Johnson", 18));
        q.add(new Student("Danielle Williams", 17));

        System.out.println(q);
    }

}
