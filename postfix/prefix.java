package cs2.postfix;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.print.DocFlavor;
import java.util.Scanner;
import java.util.Stack;

public class prefix {
    public static void main(String[] args) {
        System.out.println("Enter Expression: ");
        Scanner rdr = new Scanner(System.in);
        String str = rdr.next();
        Stack<String> operStack = new Stack<String>();
        Stack<Double> numStack = new Stack<Double>();
        int numOperators = 0;
        int numNums = 0;
        double val = 0.0;
        int error = 0;
        boolean numYet = false;

        while (str.equals("=") == false) {
            if (str.equals("*") || str.equals("+") || str.equals("-") || str.equals("/")) {
                numYet = false;
                operStack.push(str);
                numOperators++;
            } else {
                try {
                    val = Double.parseDouble(str);
                    numNums++;
                    numYet = true;
                    numStack.push(val);
                    Double value = 0.0;

                    if (numNums > 1) {
                        Double one = numStack.pop();
                        Double two = numStack.pop();
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
                    }
                } catch (Exception e) {
                    error = 1;
                    break;
                }
                // error checking
                numStack.push(val);
            }
            str = rdr.next();
        }

        if (numNums != numOperators + 1 || error == 1) {
            System.out.println("Invalid Expression");
        } else {
            System.out.println("Answer: " + numStack.pop());
        }

    }

}
