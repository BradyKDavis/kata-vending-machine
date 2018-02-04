package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.products.enums.ProductType;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ReturnCoinsTestSuite
{
	private Injector injector;
	private Dime dime;
	private Quarter quarter;
	
	//System under test
	private VendingMachine vendingMachine;
	
	@Before
	public void setUp() throws Exception
	{
		injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
		dime = new Dime();
		quarter = new Quarter();
	}

	@Test
	public void whenReturnCoinsPressedWithNoCoinsInsertedNoCoinsReturned()
	{
		//arrange
		ArrayList<ICoin> expected = new ArrayList<ICoin>();
		
		//act
		vendingMachine.returnCoins();
		ArrayList<ICoin> actual = vendingMachine.getCoinReturn();
		
		//assert
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
	@Test 
	public void whenReturnCoinsPressedWithCoinsInsertedThoseCoinsAreReturned()
	{
		//arrange
		ArrayList<ICoin> coins = new ArrayList<ICoin>();
		coins.add(quarter);
		coins.add(quarter);
		coins.add(dime);
		
		//act
		for(ICoin coin : coins)
		{
			vendingMachine.insertCoin(coin);
		}
		vendingMachine.returnCoins();
		
		//assert
		assertEquals(coins, vendingMachine.getCoinReturn());
	}
	
	@Test
	public void whenReturnCoinsPressedWithCoinsInsertedVendingMachineDoesNotRetainBalance()
	{
		//arrange
		//act
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.returnCoins();
		vendingMachine.selectProduct(ProductType.CHIPS);
		
		//assert
		assertNull(vendingMachine.getDispensedProduct());
	}
	
	@Test
	public void whenReturnCoinsPressedWithCoinsInsertedTheMessageDisplayResetsToInsertMessage()
	{
		//arrange
		//act
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.returnCoins();
		
		//assert
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}

}
