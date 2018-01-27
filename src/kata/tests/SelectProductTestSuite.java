package kata.tests;

import static org.junit.Assert.*;
import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.products.IProduct;
import kata.products.enums.ProductType;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

public class SelectProductTestSuite 
{
	private VendingMachine vendingMachine;
	
	private Quarter quarter;
	private Dime dime;
	private Nickel nickel;
	
	IProduct product;
	
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
	public void dispenseProductForColaWithOneDollarDispensesCola() 
	{
		insertMoney(new Float(1));
		vendingMachine.selectProduct(ProductType.COLA);
		product = vendingMachine.getDispensedProduct();
		assertNotNull(product);
		assertEquals(ProductType.COLA, product.getProductType());
	}
	
	@Test 
	public void dispenseProductForChipsWithFiftyCentsDispensesChips()
	{
		insertMoney(new Float(.50));
		vendingMachine.selectProduct(ProductType.CHIPS);
		product = vendingMachine.getDispensedProduct();
		assertNotNull(product);
		assertEquals(ProductType.CHIPS, product.getProductType());
	}

}
