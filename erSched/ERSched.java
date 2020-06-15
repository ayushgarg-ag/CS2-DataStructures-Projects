package cs2.erSched;

import cs2.queue.CS2PriorityQueue;

import java.util.PriorityQueue;
import java.util.Scanner;

public class ERSched {
    public static void main(String[] args) {
        CS2PriorityQueue<Patient> patientList = new CS2PriorityQueue<Patient>();
        boolean exit = false;
        while (exit == false) {
            boolean success = false;
            Scanner rdr = new Scanner(System.in);
            int input = 0;

            while (success == false) {
                System.out.println("1. Schedule a patient\n2. Treat the next patient\n3. Treat all patients\n4. View current queue\n5. Change patient's condition \n6. Exit the program");
                System.out.println("Enter a number (1-6): ");
                String resp = rdr.next();

                try {
                    input = Integer.parseInt(resp);
                    success = true;
                    if (input <= 0 || input >=7) {
                        System.out.println("That is not a valid option. Try again.\n");
                        success = false;
                    }
                } catch (Exception e) {
                    System.out.println("That is not a valid option. Try again.\n");
                    success = false;
                }
            }

            if (input == 1) {
                System.out.println("Enter the patient's name");
                String name = rdr.next();
                int num = 0;
                success = false;
                while (success == false) {
                    System.out.println("1. Critical\n2. Serious\n3. Fair");
                    String resp = rdr.next();

                    try {
                        num = Integer.parseInt(resp);
                        success = true;
                        if (num <= 0 || num >= 4) {
                            System.out.println("That is not a valid option. Try again.\n");
                            success = false;
                        }
                    } catch (Exception e) {
                        System.out.println("That is not a valid option. Try again.\n");
                        success = false;
                    }
                }

                Condition con = Condition.critical;
                if (num == 1) {
                    con = Condition.critical;
                } else if (num == 2) {
                    con = Condition.serious;
                } else {
                    con = Condition.fair;
                }
                patientList.add(new Patient(name, con));
                System.out.println(name + " is added to the " + con + " list");
            } else if (input == 2) {
                if (patientList.size() == 0) {
                    System.out.println("There are no patients to treat.");
                }
                else {
                    Patient p = patientList.remove();
                    System.out.println(p.getName() + " is being treated");
                }
            } else if (input == 3) {
                int numPatients = patientList.size();
                if (numPatients == 0) {
                    System.out.println("There are no patients to treat.");
                }
                else {
                    for (int i = 0; i < numPatients; i++) {
                        Patient p = patientList.remove();
                        System.out.println(p.getName() + " is being treated");
                    }
                }
            } else if (input == 4) {
                System.out.println("Current Waiting Queue: " + patientList);
            } else if (input == 5) {
                System.out.println("Enter the name of the patient whose condition you would like to change?");
                String name = rdr.next();

                System.out.println("What is " + name + "'s new condition");
                success = false;
                int num = 0;
                while (success == false) {
                    System.out.println("1. Critical\n2. Serious\n3. Fair");
                    String resp = rdr.next();

                    try {
                        num = Integer.parseInt(resp);
                        success = true;
                        if (num <= 0 || num >= 4) {
                            System.out.println("That is not a valid option. Try again.\n");
                            success = false;
                        }
                    } catch (Exception e) {
                        System.out.println("That is not a valid option. Try again.\n");
                        success = false;
                    }
                }
                Condition con = Condition.critical;
                if (num == 1) {
                    con = Condition.critical;
                } else if (num == 2) {
                    con = Condition.serious;
                } else {
                    con = Condition.fair;
                }
                int numPatients = patientList.size();
                success = false;
                for (int i = 0; i < numPatients; i++) {
                    Patient p = patientList.get(i);
                    if (p.getName().equals(name)) {
                        p.setCondition(con);
                        Patient other = new Patient(name, con);
                        patientList.remove(i);
                        patientList.add(other);
                        System.out.println(name + "'s condition has changed and their position in the queue has been updated");
                        success = true;
                    }
                }
                if (success == false) {
                    System.out.println("No one in the current waiting queue matches that name");
                }
            }
            else {
                exit = true;
            }
            System.out.println();
        }
        System.out.println("Program exited.");
    }
}
