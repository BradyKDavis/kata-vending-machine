package kata.tests;

import static org.junit.Assert.*;
import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

public class AcceptCoinsTestSuite 
{
	private VendingMachine vendingMachine;
	
	@Before
	public void setUp()
	{
		vendingMachine = new VendingMachine();
	}
	
	@Test
	public void whenVendingMachineHasNoCoinsInsertedDisplayShowsINSERTCOIN() 
	{
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineAcceptsANickelItUpdatesDisplayToShow5Cents()
	{
		vendingMachine.insertCoin(new Nickel());
		assertEquals("0.05", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineAcceptsADimeItUpdatesDisplayToShow10Cents()
	{
		vendingMachine.insertCoin(new Dime());
		assertEquals("0.10", vendingMachine.getDisplayMessage());
	}

	
	@Test
	public void whenVendingMachineAcceptsAQuarterItUpdatesDisplayToShow25Cents()
	{
		vendingMachine.insertCoin(new Quarter());
		assertEquals("0.25", vendingMachine.getDisplayMessage());
	}
}
