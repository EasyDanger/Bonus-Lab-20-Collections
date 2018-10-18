package bonusLab20;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BonusLab20 {
	static Map<String, Double> priceList = new HashMap<>();
	static List<String> shoppingCart = new LinkedList<>();
	static Scanner read = new Scanner(System.in);
	static double currentTotal;
	static boolean finished = false;
	public static void main(String[] args) {

		// Welcome.
		System.out.println("Welcome to the Carter Family Coop Foodshare Community Marketplace!\n");
		// List of items and prices placed in the priceList Map.
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
		// User Prompt.
//		do {
//			try {
				do {
		System.out.println(
				"What item would you like to add to your shopping cart?\n(To see a list of items, type \"menu\".)");

		String menuChoice = read.nextLine();
		if (menuChoice.equalsIgnoreCase("menu")) {
			priceList();
			finished = false;
		} else if (menuChoice.equalsIgnoreCase(stringIsKey(menuChoice, priceList))) {
			String key = stringIsKey(menuChoice, priceList);
			addToCart(key);
			System.out.println("Are you finished adding items? (y/n)");
			String answer = read.nextLine();
			finished = checkYes(answer);
		} else {
			System.out.println("We're sorry, but we don't have that item available. :( \nPlease select another item from the list of available menu items. \n");
			finished = false;
			}
				}while (!finished);
				
				orderComplete();
//				// Prompts to look up another student.
//				System.out.println("Would you like to find out about another student? (\"yes\" to continue)");
//				// String only exists for this version of the loop.
//				String loop = read.nextLine();
//				// May consider writing a method to accept "yes," y,", their capitals, etc.
//				if (loop.equalsIgnoreCase("yes")) {
//					finished = false;
//				} else {
//					finished = true;
//				}
//			}
//// Here's the catch, catching the out of bounds exception.
//			catch (ArrayIndexOutOfBoundsException ex) {
//				// Checks which way out of bounds the input is and responds appropriately.
//				if (indexChoice >= students.length) {
//					System.out.println(
//							"Thanks for your faith in our school! But there are, unfortunately, only 16 students enrolled here. Please search between 1 and 16.");
//				} else {
//					System.out.println(
//							"Aw, come on! Have a little faith in our school! We've got 16 students to choose from.");
//				}
//				// Still looping the same way.
//				finished = false;
//			}
//		} while (!finished);
//// When it' sall said and done, we say goodbye.
//		System.out.println("Thank you for accessing the Sky High Student Database.\nGoodbye.");

		read.close();

	}

	private static void addToCart(String key) {
		System.out.println(key + " costs $" + priceList.get(key) + ".\nAre you sure you want to add " + key + " to your shopping cart?");
		String answer = read.nextLine();
		if (checkYes(answer)) {
			shoppingCart.add(key);
			System.out.println(key + " has/have been added to your cart!\n");
		} else {
		}
		currentTotal = 0;
		for (int i = 0; i < shoppingCart.size(); i++) {

			currentTotal += priceList.get(shoppingCart.get(i));
		}
		System.out.println("Your current total is $" + currentTotal);
	}

	public static boolean checkYes(String yes) {
		boolean isYes;
		if (yes.equalsIgnoreCase("y") || yes.equalsIgnoreCase("yes")) {
			isYes = true;
		} else {
			isYes = false;
		}
		return isYes;
	}

	public static String stringIsKey(String menuChoice, Map<String, Double> priceList2) {
		Set<String> keys = priceList2.keySet();
		Iterator<String> iter = keys.iterator();
		String key;
		do {
			key = iter.next();
		} while (iter.hasNext() && !key.equalsIgnoreCase(menuChoice));
		return key;
	}

//Method to print the Complete List of Prices
	private static void priceList() {

		System.out.println("Item                 Price");
		System.out.println("==========================");

		Set<String> keys = priceList.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.printf("%-14s", key);
			System.out.printf("%12s", "$" + priceList.get(key));
			System.out.println("");
		}
	}
	private static void orderComplete() {
		System.out.println("Okay, here's your complete order. Please verify below.\n");
		System.out.println("Item                Price");
		System.out.println("=========================");
		
		for (int i = 0; i < shoppingCart.size(); i++) {
		System.out.printf("%-14s", shoppingCart.get(i));
		System.out.printf("%12s", "$" + priceList.get(shoppingCart.get(i)) + "\n");		
		}
		System.out.println(average());
		}
	private static String average() {
		String strForFPoint = String.format("%.2f", (currentTotal / shoppingCart.size()));
	return "Average price per item: " + strForFPoint;
	}
}
