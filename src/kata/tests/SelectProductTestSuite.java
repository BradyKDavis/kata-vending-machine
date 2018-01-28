package kata.tests;

import static org.junit.Assert.*;
import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.products.IProduct;
import kata.products.enums.ProductType;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class SelectProductTestSuite 
{
	private Injector injector;
	
	//System under test
	private VendingMachine vendingMachine;
	
	private Quarter quarter;
	private Dime dime;
	private Nickel nickel;
	
	IProduct product;
	
	@Before
	public void setup()
	{
		injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
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
	
	@Test
	public void dispenseProductForCandyWithSixtyFiveCentsDispensesCandy()
	{
		insertMoney(new Float(.65));
		vendingMachine.selectProduct(ProductType.CANDY);
		product = vendingMachine.getDispensedProduct();
		assertNotNull(product);
		assertEquals(ProductType.CANDY, product.getProductType());
	}
	
	@Test
	public void whenProductHasDispensedVendingMachineReadsThankYou()
	{
		insertMoney(new Float(1));
		vendingMachine.selectProduct(ProductType.COLA);
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void afterVendingMachineReadsThankYouItThenReadsInsertCoin()
	{
		insertMoney(new Float(.65));
		vendingMachine.selectProduct(ProductType.CANDY);
		vendingMachine.getDisplayMessage();
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void ifInsufficientMoneyProvidedForProductThenProductIsNotDispensed()
	{
		insertMoney(new Float(.40));
		vendingMachine.selectProduct(ProductType.CANDY);
		assertNull(vendingMachine.getDispensedProduct());
		vendingMachine.selectProduct(ProductType.CHIPS);
		assertNull(vendingMachine.getDispensedProduct());
		vendingMachine.selectProduct(ProductType.COLA);
		assertNull(vendingMachine.getDispensedProduct());
	}
}
