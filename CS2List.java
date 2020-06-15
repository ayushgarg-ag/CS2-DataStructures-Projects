package cs2;

public interface CS2List<E> {
    int size ();
    boolean add (E obj);
    void add (int index, E obj);
    E get (int index);
    E set (int index, E obj);
    E remove (int index);
}
