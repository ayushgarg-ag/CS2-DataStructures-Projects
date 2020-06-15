package cs2.stack;

public class Runner {
    public static void main(String[] args) {
        CS2Stack<String> stk = new CS2Stack<String>();
        System.out.println(stk);
        stk.push("A");
        System.out.println(stk);
        stk.push("B");
        System.out.println(stk);
        stk.push("C");
        System.out.println(stk);
        System.out.println(stk.pop() + " " + stk);
        stk.push("D");
        System.out.println(stk);
        System.out.println(stk.pop() + " " + stk);
        System.out.println(stk.pop() + " " + stk);
        System.out.println(stk.pop() + " " + stk);

    }
}
