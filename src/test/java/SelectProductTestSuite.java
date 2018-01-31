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
		insertMoney(new BigDecimal("1.00"));
		vendingMachine.selectProduct(ProductType.COLA);
		product = vendingMachine.getDispensedProduct();
		assertNotNull(product);
		assertEquals(ProductType.COLA, product.getProductType());
	}
	
	@Test 
	public void dispenseProductForChipsWithFiftyCentsDispensesChips()
	{
		insertMoney(new BigDecimal("0.50"));
		vendingMachine.selectProduct(ProductType.CHIPS);
		product = vendingMachine.getDispensedProduct();
		assertNotNull(product);
		assertEquals(ProductType.CHIPS, product.getProductType());
	}
	
	@Test
	public void dispenseProductForCandyWithSixtyCentsDispensesCandy()
	{
		insertMoney(new BigDecimal("0.65"));
		vendingMachine.selectProduct(ProductType.CANDY);
		product = vendingMachine.getDispensedProduct();
		assertNotNull(product);
		assertEquals(ProductType.CANDY, product.getProductType());
	}
	
	@Test
	public void whenProductHasDispensedVendingMachineReadsThankYou()
	{
		insertMoney(new BigDecimal("1.00"));
		vendingMachine.selectProduct(ProductType.COLA);
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void afterVendingMachineReadsThankYouItThenReadsInsertCoin()
	{
		insertMoney(new BigDecimal("0.65"));
		vendingMachine.selectProduct(ProductType.CANDY);
		vendingMachine.getDisplayMessage();
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void ifInsufficientMoneyProvidedForProductThenProductIsNotDispensed()
	{
		insertMoney(new BigDecimal("0.40"));
		vendingMachine.selectProduct(ProductType.CANDY);
		assertNull(vendingMachine.getDispensedProduct());
		vendingMachine.selectProduct(ProductType.CHIPS);
		assertNull(vendingMachine.getDispensedProduct());
		vendingMachine.selectProduct(ProductType.COLA);
		assertNull(vendingMachine.getDispensedProduct());
	}
	
	@Test
	public void ifInsufficientMoneyProviedForProductThenDisplaysProductPrice()
	{
		insertMoney(new BigDecimal("0.40"));
		vendingMachine.selectProduct(ProductType.CHIPS);
		assertEquals("PRICE 0.50", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void afterProductPriceDisplayedWithZeroMoneyInsertCoinIsDisplayed()
	{
		vendingMachine.selectProduct(ProductType.CANDY);
		assertEquals("PRICE 0.65", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void afterProductPriceDisplayedWithMoneyInsertedCurrentBalanceIsDisplayed()
	{
		insertMoney(new BigDecimal("0.40"));
		vendingMachine.selectProduct(ProductType.CHIPS);
		assertEquals("PRICE 0.50", vendingMachine.getDisplayMessage());
		assertEquals("0.40", vendingMachine.getDisplayMessage());
	}
}
