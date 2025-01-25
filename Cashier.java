package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Myra Ribeiro
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Reads keyboard input. Returns a positive quantity, or 0 if the input is not a
     * valid positive integer.
     *
     * @param scan A keyboard Scanner.
     * @return
     */
    private int askForQuantity(Scanner scan) {
	int quantity = 0;
	System.out.print("How many do you want? ");

	try {
	    String line = scan.nextLine();
	    quantity = Integer.parseInt(line);
	} catch (NumberFormatException nfex) {
	    System.out.println("Not a valid number");
	}
	return quantity;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");

	// your code here
	Scanner scan = new Scanner(System.in);
	Order order = new Order();
	printCommands(); // Display the menu

	while (true) {
	    System.out.print("Command: ");
	    String input = scan.nextLine();

	    try {
		int command = Integer.parseInt(input);
		if (command == 0) { // End the order
		    break;
		} else if (command > 0 && command <= menu.size()) {
		    MenuItem item = menu.getItem(command - 1);
		    int quantity = askForQuantity(scan);
		    if (quantity > 0) {
			order.add(item, quantity);
		    }
		} else {
		    printCommands(); // Invalid item number
		}
	    } catch (NumberFormatException e) {
		System.out.println("Not a valid number");
		printCommands();
	    }
	}

	System.out.println("----------------------------------------");
	System.out.println("Receipt");
	System.out.println(order.toString());

	return order;
    }
}