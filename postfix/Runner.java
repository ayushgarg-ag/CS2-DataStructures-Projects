package cs2.postfix;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;
import java.util.Stack;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Enter Expression: ");
        Scanner rdr = new Scanner(System.in);
        String str = rdr.next();
        Stack<Double> stack = new Stack<Double>();
        int numOperators = 0;
        int numNums = 0;
        double val = 0.0;
        int error = 0;

        while (str.equals("=") == false) {
            if (str.equals("*") || str.equals("+") || str.equals("-") || str.equals("/")) {
                Double value = 0.0;
                Double one, two;
                try {
                    two = stack.pop();
                    one = stack.pop();
                } catch (Exception e) {
                    error = 1;
                    break;
                }
                if (str.equals("*")) {
                    value = one * two;
                }
                if (str.equals("+")) {
                    value = one + two;
                }
                if (str.equals("-")) {
                    value = one - two;
                }
                if (str.equals("/")) {
                    value = one / two;
                }
                stack.push(value);
                numOperators++;
            } else {
                try {
                    val = Double.parseDouble(str);
                    numNums++;
                } catch (Exception e) {
                    error = 1;
                    break;
                }
                // error checking
                stack.push(val);
            }

            str = rdr.next();
        }

        if (numNums != numOperators + 1 || error == 1) {
            System.out.println("Invalid Expression");
        } else {
            System.out.println("Answer: " + stack.pop());
        }

    }

}
