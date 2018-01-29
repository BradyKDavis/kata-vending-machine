package kata.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.products.enums.ProductType;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MakeChangeTestSuite
{
	private static final BigDecimal QUARTER_VALUE = new BigDecimal(".25");
	private static final BigDecimal DIME_VALUE = new BigDecimal(".10");
	private static final BigDecimal NICKEL_VALUE = new BigDecimal(".05");
	
	private Injector injector;
	
	//System Under Test
	private VendingMachine vendingMachine;
	
	private Nickel nickel;
	private Dime dime;
	private Quarter quarter;

	
	private ArrayList<ICoin> coins;
	
	@Before
	public void setUp()
	{
		injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
		nickel = new Nickel();
		dime = new Dime();
		quarter = new Quarter();
		coins = new ArrayList<ICoin>();
	}
	
	@Test
	public void whenProductPurchasedWithNickelOverCoinReturnContainsNickel()
	{
		insertMoney(new BigDecimal("0.55"));
		vendingMachine.selectProduct(ProductType.CHIPS);
		coins.add(nickel);
		assertArrayEquals(coins.toArray(), vendingMachine.getCoinReturn().toArray());
	}
	
	@Test
	public void whenProductPurchasedWithDimeOverCoinReturnContainsDime()
	{
		insertMoney(new BigDecimal("1.10"));
		vendingMachine.selectProduct(ProductType.COLA);
		coins.add(dime);
		assertArrayEquals(coins.toArray(), vendingMachine.getCoinReturn().toArray());
	}
	
	@Test
	public void whenProductPurchasedWithQuarterOverCoinReturnContainsQuarter()
	{
		insertMoney(new BigDecimal(".90"));
		vendingMachine.selectProduct(ProductType.CANDY);
		coins.add(quarter);
		assertArrayEquals(coins.toArray(), vendingMachine.getCoinReturn().toArray());
	}
	
	@Test
	public void whenProductPurchasedWithMultipleCoinsOverCoinReturnReturnsThoseCoins()
	{
		insertMoney(new BigDecimal("1.45"));
		vendingMachine.selectProduct(ProductType.COLA);
		coins.add(quarter);
		coins.add(dime);
		coins.add(dime);
		assertArrayEquals(coins.toArray(), vendingMachine.getCoinReturn().toArray());
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
