package kata.tests;

import static org.junit.Assert.*;
import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Penny;
import kata.coins.Quarter;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

public class AcceptCoinsTestSuite 
{
	private VendingMachine vendingMachine;
	private Nickel nickel;
	private Dime dime;
	private Quarter quarter;
	private Penny penny;
	
	@Before
	public void setUp()
	{
		vendingMachine = new VendingMachine();
		nickel = new Nickel();
		dime = new Dime();
		quarter = new Quarter();
		penny = new Penny();
	}
	
	@Test
	public void whenVendingMachineHasNoCoinsInsertedDisplayShowsINSERTCOIN() 
	{
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineAcceptsANickelItUpdatesDisplayToShow5Cents()
	{
		vendingMachine.insertCoin(nickel);
		assertEquals("0.05", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineAcceptsADimeItUpdatesDisplayToShow10Cents()
	{
		vendingMachine.insertCoin(dime);
		assertEquals("0.10", vendingMachine.getDisplayMessage());
	}

	
	@Test
	public void whenVendingMachineAcceptsAQuarterItUpdatesDisplayToShow25Cents()
	{
		vendingMachine.insertCoin(quarter);
		assertEquals("0.25", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachineAcceptsMultipleDifferentCoinsAndUpdatesDisplayToCorrectTotal()
	{
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(nickel);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(quarter);
		assertEquals("1.10", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachineDoesNotAcceptPenniesAndDoesNotIncreaseInValueWhenPennyAdded()
	{
		vendingMachine.insertCoin(penny);
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
}
