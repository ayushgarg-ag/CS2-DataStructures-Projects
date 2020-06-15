package cs2.heap;

public class HeapStudentRunner {

    public static void main(String[] args) {

        Heap<Student> q = new Heap<Student>();
        Student.setDir (Student.ASC);

        q.add (new Student ("Julius Fleming", 16));
        q.add (new Student ("Roberta Adkins", 16));
        q.add (new Student ("Bernice Jefferson", 18));
        q.add (new Student ("Gwen Malone", 17));
        q.add (new Student ("Dustin Bowers", 17));
        q.add (new Student ("Daisy Powell", 18));
        q.add (new Student ("Ramona Briggs", 16));
        q.add (new Student ("Kerry Dennis", 16));
        q.add (new Student ("Colin Johnson", 18));
        q.add (new Student ("Danielle Williams", 17));

        System.out.println("Remove students one at a time");
        for (int i = q.size(); i > 0; i--) {
            Student st = q.remove();
            System.out.println(st.name + " who is " + st.age + " years old was removed");
        }

    }

}
