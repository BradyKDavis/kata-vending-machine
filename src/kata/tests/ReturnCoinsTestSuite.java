package kata.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ReturnCoinsTestSuite
{
	private Injector injector;
	
	private Nickel nickel;
	private Dime dime;
	private Quarter quarter;
	
	//System under test
	private VendingMachine vendingMachine;
	
	@Before
	public void setUp() throws Exception
	{
		injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
		nickel = new Nickel();
		dime = new Dime();
		quarter = new Quarter();
	}

	@Test
	public void whenReturnCoinsPressedWithNoCoinsInsertedNoCoinsReturned()
	{
		ArrayList<ICoin> expected = new ArrayList<ICoin>();
		vendingMachine.returnCoins();
		ArrayList<ICoin> actual = vendingMachine.getCoinReturn();
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
	@Test 
	public void whenReturnCoinsPressedWithCoinsInsertedThoseCoinsAreReturned()
	{
		ArrayList<ICoin> coins = new ArrayList<ICoin>();
		coins.add(quarter);
		coins.add(quarter);
		coins.add(dime);
		for(ICoin coin : coins)
		{
			vendingMachine.insertCoin(coin);
		}
		vendingMachine.returnCoins();
		assertEquals(coins, vendingMachine.getCoinReturn());
	}

}
