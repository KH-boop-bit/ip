import java.util.Scanner; //import scanner class for inputs

public class Jamal {
    public static void main(String[] args) {
        String welcome =
            "____________________________________________________________\n"
            + "Hello! I'm Jamal\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n";

        String exit =
                "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(welcome);

        Scanner scanner = new Scanner(System.in);

        String[] inputlist = new String[100];
        int inputlistcounter = 0; //global counter for number of list items

        while (true) { //keep the while loop active until exit string is executed
            System.out.println(""); //empty string placeholder for input
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) { //ignorecase so all Bye bye works
                break;
            }

            if (input.toLowerCase().equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < inputlistcounter; i++) {
                    System.out.println(
                            (i + 1) + ". "
                            + inputlist[i] + "\n");
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            System.out.println(
                    "____________________________________________________________\n"
                    + "added: " + input + "\n"
                    + "____________________________________________________________");
            inputlist[inputlistcounter] = input; //put input into array first
            inputlistcounter++; //maintain a global counter
        }
        scanner.close(); //release system resources
        System.out.println(exit);
    }
}
