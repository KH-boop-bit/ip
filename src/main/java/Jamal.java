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

        while (true) { //keep the while loop active until exit string is executed
            System.out.println(""); //empty string placeholder for input
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) { //ignorecase so all Bye bye works
                break;
            }
            System.out.println(
                    "____________________________________________________________\n"
                    + input + "\n"
                    + "____________________________________________________________");
        }
        scanner.close(); //release system resources
        System.out.println(exit);
    }
}
