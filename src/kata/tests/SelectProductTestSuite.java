package kata.tests;

import static org.junit.Assert.*;
import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

public class SelectProductTestSuite 
{
	private VendingMachine vendingMachine;
	
	private Quarter quarter;
	private Dime dime;
	private Nickel nickel;
	
	@Before
	public void setup()
	{
		vendingMachine = new VendingMachine();
		quarter = new Quarter();
		dime = new Dime();
		nickel = new Nickel();
	}
	
	private void insertMoney(Float money)
	{
		while(money >= .25f)
		{
			money -= .25f;
			vendingMachine.insertCoin(quarter);
		}
		while(money >= .1f)
		{
			money -= .1f;
			vendingMachine.insertCoin(dime);
		}
		while(money >= .05f)
		{
			money -= .05f;
			vendingMachine.insertCoin(nickel);
		}
	}
	
	@Test
	public void TestDispenseProductForColaWithOneDollarDispensesCola() 
	{
		insertMoney(1f);
		vendingMachine.selectProduct(Product.COLA);
		assertNotNull(vendingMachine.getDispensedProduct());
		assertEquals(Product.COLA, vendingMachine.getDispsensedProduct());
	}

}
