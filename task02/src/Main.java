import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      double last = 0;

      while (true) {
        System.out.print("> ");

        // Read the input from the user
        String input = scanner.nextLine();

        // Check if the user entered the exit command
        if (input.equals("exit")) {
          break;
        }

        // Split the input into tokens
        String[] tokens = input.split(" ");

        // Check if the input is a valid command
        if (tokens.length != 3) {
          System.out.println("Invalid input. Please enter an operator and two operands.");
          continue;
        }

        // Parse the operands and operator
        double operand1 = 0;
        if (tokens[0].equals("$last")) {
          operand1 = last;
        } else {
          operand1 = Double.parseDouble(tokens[0]);
        }
        double operand2 = 0;
        if (tokens[2].equals("$last")) {
          operand2 = last;
        } else {
          operand2 = Double.parseDouble(tokens[2]);
        }
        String operator = tokens[1];

        // Perform the calculation
        double result = 0;
        switch (operator) {
          case "+":
            result = operand1 + operand2;
            break;
          case "-":
            result = operand1 - operand2;
            break;
          case "*":
            result = operand1 * operand2;
            break;
          case "/":
            result = operand1 / operand2;
            break;
          default:
            System.out.println("Invalid operator. Please enter +, -, *, or /.");
            continue;
        }

        // Update the value of last and print the result
        last = result;
        System.out.println(result);
      }
    }
  }
}
