package bonusLab20;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BonusLab20 {
	// Map of the available items for sale.
	static Map<String, Double> priceList = new HashMap<>();
	// Where list of items added to shopping cart will go.
	static List<String> shoppingCart = new LinkedList<>();
	// Scanner
	static Scanner read = new Scanner(System.in);
	// Holds the customer's total bill.
	static double currentTotalInt;
	// Used to loop while loops. Primarily for menus.
	static boolean finished = false;
	static String currentTotal;

	public static void main(String[] args) {
		// Welcome.
		System.out.println("Welcome to the Chozo Family Coop Foodshare Community Marketplace Bazaar!\n");
		// List of items and prices placed in the priceList Map. Could probably have
		// been placed with the other variables, but I'm experimenting with different
		// placements.
		priceList.put("Bombs", 799.99);
		priceList.put("Missiles", 99.99);
		priceList.put("Super Missiles", 399.99);
		priceList.put("Power Bombs", 399.99);
		priceList.put("Ice Beam", 199.99);
		priceList.put("Spazer Beam", 249.99);
		priceList.put("Wave Beam", 799.99);
		priceList.put("Plasma Beam", 849.99);
		priceList.put("Screw Attack", 499.99);
		priceList.put("Springball", 9999.99);
		priceList.put("E-Tanks", 199.99);
		priceList.put("Reserve Tank", 0.99);
		priceList.put("Varia Suit", 599.99);
		priceList.put("Gravity Suit", 799.99);
		priceList.put("Speed Booster", 599.99);
		priceList.put("High Jump Boots", 749.99);
//Menu structure is copied from previous lab.
		do {
			// User prompt.
			System.out.println(
					"What item would you like to add to your shopping cart?\n(To see a list of items, type \"menu\".)");
			// menuChoice only matters to this loop. May need to organize later instances of
			// menuChoice v. key variable that will come up.
			String menuChoice = read.nextLine();
			// Calls the priceList method to display the available items. Keeps the finished
			// = false to loop.
			if (menuChoice.equalsIgnoreCase("menu")) {
				priceList();
				finished = false;
			}
			// else if checks the entered menu choice is in the pricelist map, then calls
			// the addToCart method to deal with adding it if it is. Otherwise, loops.
			else if (menuChoice.equalsIgnoreCase(stringIsKey(menuChoice, priceList))) {
				// Simpler to store the keys in a variable than to keep calling this method on
				// the key and map. Also, code is easier to follow IMO if the map key is called
				// such.
				String key = stringIsKey(menuChoice, priceList);
				addToCart(key);
				System.out.println("Are you finished adding items? (y/n)");
				String answer = read.nextLine();
				// Stores boolean from checkYes. Simpler and cleaner up here than continuing to
				// write code to accept yes's.
				finished = checkYes(answer);
			}
			// Loops if the item isn't a menu item or the "menu" item.
			else {
				System.out.println(
						"We're sorry, but we don't have that item available. :( \nPlease select another item from the list of available menu items. \n");
				finished = false;
			}
		} while (!finished);
		// Calls method to complete the bill.
		orderComplete();
		read.close();

	}

//Method to add items to the user's chopping cart.
	private static void addToCart(String key) {
		// User prompt.
		System.out.println(key + " costs $" + priceList.get(key) + ".\nAre you sure you want to add " + key
				+ " to your shopping cart?");
		// answer is only needed here. Verifies user wants to add item.
		String answer = read.nextLine();
		if (checkYes(answer)) {
			//Calls howMany() method to allow user to order multiple of items at once.
			int x = howMany(key);
			for (int i = 0; i < x; i++) {
						shoppingCart.add(key);	
			}
			System.out.println(key + " has/have been added to your cart " + x + " times!\n");
		} else {
		}
		// Cleared here, as for loop will iterate to add every item's price that is
		// stored in the shoppingCart list.
		currentTotalInt = 0;
		for (int i = 0; i < shoppingCart.size(); i++) {
			// iterates to call each item in shoppingCart as the key for priceList, and adds
			// this price to the current total.
			currentTotalInt += priceList.get(shoppingCart.get(i));
			currentTotal = String.format("%.2f", (currentTotalInt));
		}
		// Prints total only of items added to list.
		System.out.println("Your current total is $" + currentTotal);
	}

//Method to check for affirmative answers
	public static boolean checkYes(String yes) {
		boolean isYes;
		// Method will accept yes or no. Basically gives user a direct way to affect the
		// finished boolean.
		if (yes.equalsIgnoreCase("y") || yes.equalsIgnoreCase("yes")) {
			isYes = true;
		} else {
			isYes = false;
		}
		return isYes;
	}

//Method to determine whether the user entered string is equal to a key in my map. Similar to previous project's method of finding out if an input exists in an array. Will return the key for simplicity of variables as mentioned earlier.
	public static String stringIsKey(String menuChoice, Map<String, Double> priceList2) {
		// I adapted this code from the thing I found below in the priceList() method
		Set<String> keys = priceList2.keySet();
		Iterator<String> iter = keys.iterator();
		String key;
		// Loops through, comparing the given string to all keys in the map. This is
		// written with generic variables to add to a list of growing validation style
		// methods for future use.
		do {
			key = iter.next();
		} while (iter.hasNext() && !key.equalsIgnoreCase(menuChoice));
		return key;
	}

//Method to print the Complete List of Prices
	private static void priceList() {
		// Table to display all items available for purchase.
		System.out.println("Item                 Price");
		System.out.println("==========================");
		// I found this code for iterating through the list for this purpose. Simple and
		// short, but I need to study it better for better understanding. in this case,
		// it lets me iterate through the map to print all it's items.
		Set<String> keys = priceList.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.printf("%-14s", key);
			System.out.printf("%12s", "$" + priceList.get(key));
			System.out.println("");
		}
	}

//Method to display complete bill.
	private static void orderComplete() {
		// Formatting is hard. These tables should be basically the same, but they're
		// not for some reason. This table will display the items from the shopping list
		// along with their prices pulled from the priceList.
		System.out.println("Okay, here's your complete order. Please verify below.\n");
		System.out.println("Item                Price");
		System.out.println("=========================");

		for (int i = 0; i < shoppingCart.size(); i++) {
			System.out.printf("%-14s", shoppingCart.get(i));
			System.out.printf("%12s", "$" + priceList.get(shoppingCart.get(i)) + "\n");
		}
		// Total still store in currentTotalInt, since line where it's cleared isn't
		// returned to. Average is called from average() method.
		System.out.println("Your total is $" + currentTotal);
		System.out.println(average());
	}

//Method to calculate and format the average price. That 'F' in the strForFPoint does NOT stand for "Floating," btw.
	private static String average() {
		String strForFPoint = String.format("%.2f", (currentTotalInt / shoppingCart.size()));
		return "Average price per item: $" + strForFPoint;
	}
//Method to allow user to add multiple of items at once.
	private static int howMany(String key) {
		System.out.println("How many of " + key + " would you like to order?");
		String count = read.nextLine();
		int countInt = 1;
		if (count.matches("\\d+")) {
			countInt = Integer.parseInt(count);
		}
		else {
			System.out.println("Sorry, we need you to enter a whole number.");
			howMany(key);
		}
		return countInt;
	}
	
}
