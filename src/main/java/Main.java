package main.java;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Penny;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.products.IProduct;
import kata.products.enums.ProductType;
import kata.vendingMachine.VendingMachine;

public class Main 
{	
	private static final String QUARTER = "Q";
	private static final String DIME = "D";
	private static final String NICKEL = "N";
	private static final String PENNY = "P";
	private static final String RETURN_COINS = "R";
	private static final String COLA = "COLA";
	private static final String CANDY = "CANDY";
	private static final String CHIPS = "CHIPS";
	private static final String HELP = "HELP";
	private static final String CHECK_DISPLAY = "DISPLAY";
	private static final String EXIT = "EXIT";
	
	private static VendingMachine vendingMachine;
	
	public static void main(String[] args) 
	{
		Injector injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		System.out.println("Welcome to the Kata Vending Machine. It accepts nickels, dimes, and quarters.");
		System.out.println();
		printHelpMessage();
		while(!input.trim().equals(EXIT))
		{
			System.out.println("The vending machine displays : " + vendingMachine.getDisplayMessage());
			System.out.print("Enter command: " );
			input = scanner.nextLine().toUpperCase().trim();
			if(input.equals(EXIT))
			{
				System.out.println("Thank you! The program will now exit.");
			}
			else if(input.equals(HELP))
			{
				printHelpMessage();
			}
			else if(input.equals(COLA))
			{
				vendingMachine.selectProduct(ProductType.COLA);
				checkDispensedContent();
				checkCoinReturnContents();
			}
			else if(input.equals(CANDY))
			{
				vendingMachine.selectProduct(ProductType.CANDY);
				checkDispensedContent();
				checkCoinReturnContents();
			}
			else if(input.equals(CHIPS))
			{
				vendingMachine.selectProduct(ProductType.CHIPS);
				checkDispensedContent();
				checkCoinReturnContents();
			}
			else if(input.equals(RETURN_COINS))
			{
				vendingMachine.returnCoins();
				checkCoinReturnContents();
			}
			else if(input.equals(NICKEL))
			{
				vendingMachine.insertCoin(new Nickel());
			}
			else if(input.equals(DIME))
			{
				vendingMachine.insertCoin(new Dime());
			}
			else if(input.equals(QUARTER))
			{
				vendingMachine.insertCoin(new Quarter());
			}
			else if(input.equals(PENNY))
			{
				vendingMachine.insertCoin(new Penny());
			}
			else if(input.equals(CHECK_DISPLAY))
			{
				System.out.println("The vending machine currently displays : " + vendingMachine.getDisplayMessage());
			}
			else
			{
				System.out.println("Unrecognized input. For a list of accepted inputs, type 'help'");
			}
			
		}
		scanner.close();
	}

	private static void checkDispensedContent() 
	{
		IProduct product = vendingMachine.getDispensedProduct();
		if(product == null)
		{
			System.out.println("No item was dispensed.");
		}
		else
		{
			System.out.println("You take a " + product.toString() + " from the vending machine.");
			vendingMachine.removeDispensedProduct();
		}
	}

	private static void checkCoinReturnContents()
	{
		ArrayList<ICoin> coins = vendingMachine.getCoinReturn();
		if(coins.isEmpty())
		{
			System.out.println("The coin return is empty");
		}
		else
		{
			
			System.out.print("Coin return contents: ");
			for(int i = 0; i < coins.size(); i++)
			{
				System.out.print(coins.get(i).toString());
				if(i == coins.size() - 1)
				{
					System.out.println(".");
				}
				else
				{
					System.out.print(", ");
				}
			}
			System.out.println("You empty the coin return of coins.");
			vendingMachine.emptyCoinReturn();
		}
	}

	private static void printHelpMessage()
	{
		System.out.println("Accepted inputs are ");
		System.out.println("'"+ QUARTER +  "': Insert a quarter");
		System.out.println("'" + DIME + "': Insert a dime");
		System.out.println("'" + NICKEL + "': Insert a nickel");
		System.out.println("'" + PENNY + "': Insert a penny");
		System.out.println("'" + RETURN_COINS + "': Ask for coin return & receive available change.");
		System.out.println("'" + CHECK_DISPLAY + "': Check the machines current message displayed.");
		System.out.println("'" + COLA +  "': Select a cola (1.00) from the machine (if able) and get available change (if any)"); 
		System.out.println("'" + CANDY + "': Select candy (0.65) from the machine (if able) and get available change (if any)");
		System.out.println("'" + CHIPS + "': Select chips (0.50) from the machine (if able) and get available change (if any)");
		System.out.println("'" + EXIT + "': Exit the program");
		System.out.println("To see this message again, type " + HELP + "'");
		System.out.println("---------------------------------------");
	}

}
