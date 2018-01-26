package kata.tests;

import static org.junit.Assert.*;
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
		assertEquals(".05", vendingMachine.getDisplayMessage());
	}

}
