package kata.tests;

import static org.junit.Assert.*;

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
		insertMoney(new Double(.55));
		vendingMachine.selectProduct(ProductType.CHIPS);
		coins.add(nickel);
		assertArrayEquals(coins.toArray(), vendingMachine.getCoinReturn().toArray());
	}
	
	private void insertMoney(Double money)
	{
		while(money >= new Double(.25))
		{
			money -= new Double(.25);
			vendingMachine.insertCoin(quarter);
		}
		while(money >= new Double(.10))
		{
			money -= new Double(.10);
			vendingMachine.insertCoin(dime);
		}
		while(money >= new Double(.05))
		{
			money -= new Double(.05);
			vendingMachine.insertCoin(nickel);
		}
	}

}
