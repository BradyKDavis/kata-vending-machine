package test.java;

import static org.junit.Assert.*;

import java.math.BigDecimal;

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
	private static final BigDecimal QUARTER_VALUE = new BigDecimal(".25");
	private static final BigDecimal DIME_VALUE = new BigDecimal(".10");
	private static final BigDecimal NICKEL_VALUE = new BigDecimal(".05");
	
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
	
	//convenience method
	private void insertMoney(BigDecimal money)
	{
		while(money.compareTo(QUARTER_VALUE) >= 0)
		{
			money = money.subtract(QUARTER_VALUE);
			vendingMachine.insertCoin(quarter);
		}
		while(money.compareTo(DIME_VALUE) >= 0)
		{
			money = money.subtract(DIME_VALUE);
			vendingMachine.insertCoin(dime);
		}
		while(money.compareTo(NICKEL_VALUE) >= 0)
		{
			money = money.subtract(NICKEL_VALUE);
			vendingMachine.insertCoin(nickel);
		}
	}
	
	@Test
	public void dispenseProductForColaWithOneDollarDispensesCola() 
	{
		//arrange
		//act
		insertMoney(new BigDecimal("1.00"));
		vendingMachine.selectProduct(ProductType.COLA);
		product = vendingMachine.getDispensedProduct();
		
		//assert
		assertNotNull(product);
		assertEquals(ProductType.COLA, product.getProductType());
	}
	
	@Test 
	public void dispenseProductForChipsWithFiftyCentsDispensesChips()
	{
		//arrange
		//act
		insertMoney(new BigDecimal("0.50"));
		vendingMachine.selectProduct(ProductType.CHIPS);
		product = vendingMachine.getDispensedProduct();
		
		//assert
		assertNotNull(product);
		assertEquals(ProductType.CHIPS, product.getProductType());
	}
	
	@Test
	public void dispenseProductForCandyWithSixtyCentsDispensesCandy()
	{
		//arrange
		//act
		insertMoney(new BigDecimal("0.65"));
		vendingMachine.selectProduct(ProductType.CANDY);
		product = vendingMachine.getDispensedProduct();
		
		//assert
		assertNotNull(product);
		assertEquals(ProductType.CANDY, product.getProductType());
	}
	
	@Test
	public void whenProductHasDispensedVendingMachineReadsThankYou()
	{
		//arrange
		//act
		insertMoney(new BigDecimal("1.00"));
		vendingMachine.selectProduct(ProductType.COLA);
		
		//assert
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void afterVendingMachineReadsThankYouItThenReadsInsertCoin()
	{
		//arrange
		//act
		insertMoney(new BigDecimal("0.65"));
		vendingMachine.selectProduct(ProductType.CANDY);
		vendingMachine.getDisplayMessage();
		
		//assert
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void ifInsufficientMoneyProvidedForProductThenProductIsNotDispensed()
	{
		//arrange
		//act
		insertMoney(new BigDecimal("0.40"));
		vendingMachine.selectProduct(ProductType.CANDY);
		
		//assert
		assertNull(vendingMachine.getDispensedProduct());
		
		//act
		vendingMachine.selectProduct(ProductType.CHIPS);
		//assert
		assertNull(vendingMachine.getDispensedProduct());
		
		//act
		vendingMachine.selectProduct(ProductType.COLA);
		//assert
		assertNull(vendingMachine.getDispensedProduct());
	}
	
	@Test
	public void ifInsufficientMoneyProviedForProductThenDisplaysProductPrice()
	{
		//arrange
		//act
		insertMoney(new BigDecimal("0.40"));
		vendingMachine.selectProduct(ProductType.CHIPS);
		
		//assert
		assertEquals("PRICE 0.50", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void afterProductPriceDisplayedWithZeroMoneyInsertCoinIsDisplayed()
	{
		//arrange
		//act
		vendingMachine.selectProduct(ProductType.CANDY);
		
		//assert
		assertEquals("PRICE 0.65", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void afterProductPriceDisplayedWithMoneyInsertedCurrentBalanceIsDisplayed()
	{
		//arrange
		//act
		insertMoney(new BigDecimal("0.40"));
		vendingMachine.selectProduct(ProductType.CHIPS);
		
		//assert
		assertEquals("PRICE 0.50", vendingMachine.getDisplayMessage());
		assertEquals("0.40", vendingMachine.getDisplayMessage());
	}
}
