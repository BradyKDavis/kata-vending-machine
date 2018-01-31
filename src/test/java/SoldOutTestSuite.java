package test.java;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.dependency.EmptyStockDependencyModule;
import kata.products.enums.ProductType;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class SoldOutTestSuite
{
	private static final BigDecimal QUARTER_VALUE = new BigDecimal(".25");
	private static final BigDecimal DIME_VALUE = new BigDecimal(".10");
	private static final BigDecimal NICKEL_VALUE = new BigDecimal(".05");
	
	private Nickel nickel;
	private Dime dime;
	private Quarter quarter;
	
	private Injector injector;

	//System under test
	private VendingMachine vendingMachine;
	@Before
	public void setUp() 
	{
		injector = Guice.createInjector(new EmptyStockDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
		nickel = new Nickel();
		dime = new Dime();
		quarter = new Quarter();
	}

	@Test
	public void whenVendingMachineIsOutOfStockOfColaAndColaIsSelectedNoColaIsDispensed()
	{
		insertMoney(new BigDecimal("1.00"));
		vendingMachine.selectProduct(ProductType.COLA);
		assertNull(vendingMachine.getDispensedProduct());
	}
	
	@Test
	public void whenVendingMachineIsOutOfStockOfCandyAndCandyIsSelectedMessageDisplayReadsSoldOut()
	{
		insertMoney(new BigDecimal("0.65"));
		vendingMachine.selectProduct(ProductType.CANDY);
		assertNull(vendingMachine.getDispensedProduct());
		assertEquals("SOLD OUT", vendingMachine.getDisplayMessage());
	}
	
	@Test 
	public void whenVendingMachineIsOutOfStockAndNoCoinsAreInsertedMessageDisplayReadsSoldOutNotPrice()
	{
		vendingMachine.selectProduct(ProductType.CHIPS);
		assertNull(vendingMachine.getDispensedProduct());
		assertEquals("SOLD OUT", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineIsOutOfStockAndNoCoinsInsertedSelectCoinsReadsAfterSoldOut()
	{
		vendingMachine.selectProduct(ProductType.CHIPS);
		assertEquals("SOLD OUT", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineIsOutOfStockAndCoinsInsertedCoinAmountReadsAfterSoldOut()
	{
		vendingMachine.insertCoin(quarter);
		vendingMachine.selectProduct(ProductType.CANDY);
		assertEquals("SOLD OUT", vendingMachine.getDisplayMessage());
		assertEquals("0.25", vendingMachine.getDisplayMessage());
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

}
