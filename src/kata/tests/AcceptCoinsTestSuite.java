package kata.tests;

import static org.junit.Assert.*;
import kata.vendingMachine.VendingMachine;

import org.junit.Test;

public class AcceptCoinsTestSuite 
{

	@Test
	public void whenVendingMachineHasNoCoinsInsertedDisplayShowsINSERTCOIN() 
	{
		VendingMachine vendingMachine = new VendingMachine();
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}

}
